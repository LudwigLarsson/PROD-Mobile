package com.example.finalprodproject.feature_user.presentation.screens;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.example.finalprodproject.R;
import com.example.finalprodproject.databinding.ProfileFragmentBinding;
import com.example.finalprodproject.feature_user.data.models.Achievement;
import com.example.finalprodproject.feature_user.domain.helpers.UserStorageHandler;
import com.example.finalprodproject.feature_user.presentation.viewmodels.UserViewModel;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ProfileFragment extends Fragment {
    private ProfileFragmentBinding binding;
    private UserViewModel viewModel;
    private int SELECT_PHOTO_PROFILE = 1;
    private Uri uri;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ProfileFragmentBinding.inflate(inflater, container, false);

        viewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);

        binding.editProfile.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_profileFragment_to_editProfileDialogFragment);
        });

        binding.logout.setOnClickListener(v -> {
            new UserStorageHandler(requireContext()).logout();
            Navigation.findNavController(requireView()).navigate(R.id.action_profileFragment_to_authFragment);
        });

        binding.avatar.setOnClickListener(v -> {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R && ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(requireActivity(),
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R && ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_MEDIA_IMAGES)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(requireActivity(),
                        new String[]{Manifest.permission.READ_MEDIA_IMAGES},
                        1);
            } else {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");

                Intent chooserIntent = Intent.createChooser(intent, "Choose Photo");

                startActivityForResult(chooserIntent, SELECT_PHOTO_PROFILE);
            }
        });

        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        viewModel.getProfile().observe(requireActivity(), userProfile -> {
            if (userProfile != null) {
                binding.userPhone.setText(userProfile.getPhone());
                if (userProfile.getLastname() != null)
                    binding.userName.setText(userProfile.getFirstname() + " " + userProfile.getLastname());
                else binding.userName.setText(userProfile.getFirstname());
                binding.profileScores.setText(Integer.toString(userProfile.getPoints()));

                if (userProfile.getImage() != null) Glide.with(requireActivity()).load(userProfile.getImage()).into(binding.avatar);

                for (Achievement achievement: userProfile.getAchievement()) {
                    if (achievement.getName().equals("Образовака")) {
                        binding.obrazovaka.setBackground(AppCompatResources.getDrawable(requireContext(), R.drawable.obrazovaka));
                    }
                    if (achievement.getName().equals("По люБВИ")) {
                        binding.poLyubvi.setBackground(AppCompatResources.getDrawable(requireContext(), R.drawable.polyubvi));
                    }
                }
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (((requestCode == SELECT_PHOTO_PROFILE && resultCode == RESULT_OK)) && data != null) {
            if (data.getData() != null) {
                uri = data.getData();

                try {
                    final InputStream imageStream = requireActivity().getContentResolver().openInputStream(uri);
                    final Bitmap originalBitmap = BitmapFactory.decodeStream(imageStream);

                    String[] filePathColumn = { MediaStore.Images.Media.DATA };
                    Cursor cursor = requireActivity().getContentResolver().query(uri, filePathColumn, null, null, null);
                    if (cursor != null) {
                        cursor.moveToFirst();

                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                        String imagePath = cursor.getString(columnIndex);
                        cursor.close();

                        File file = new File(imagePath);
                        saveImage(file, originalBitmap);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                Bundle extras = data.getExtras();
                Bitmap bitmap = (Bitmap) extras.get("data");
                File f = new File(requireContext().getCacheDir(), "test");
                try {
                    f.createNewFile();
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 0, bos);

                    byte[] bitmapdata = bos.toByteArray();

                    FileOutputStream fos = new FileOutputStream(f);
                    fos.write(bitmapdata);
                    fos.flush();
                    fos.close();

                    saveImage(f, bitmap);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void saveImage(File file, Bitmap originalBitmap) {
        binding.avatar.setImageBitmap(originalBitmap);
        viewModel.savePhoto(file);
    }
}