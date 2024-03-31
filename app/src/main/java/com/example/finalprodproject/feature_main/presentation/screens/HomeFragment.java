package com.example.finalprodproject.feature_main.presentation.screens;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.finalprodproject.R;
import com.example.finalprodproject.databinding.HomeFragmentBinding;
import com.example.finalprodproject.feature_main.data.RoadmapItem;
import com.example.finalprodproject.feature_main.presentation.adapters.GraphAdapter;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private HomeFragmentBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = HomeFragmentBinding.inflate(inflater, container, false);


        ArrayList<RoadmapItem> roadmapItems = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            roadmapItems.add(new RoadmapItem("Прямоугольный треугольник" + i, "https://sun1-30.userapi.com/impf/IIhTXspuQTOUh_F0iytqrYnHI-HH8icB61fvsg/P63gBbz4LO4.jpg?size=495x495&quality=100&sign=e5e94b6af75c4f429233d3d023155ca1&c_uniq_tag=2au4eG2oQU-GuN6idcFIfpZk5gf4tqInX2NXx1Q25GI", 10));
        }
        GraphAdapter graphAdapter = new GraphAdapter(roadmapItems);
        binding.graphList.setAdapter(graphAdapter);
        // перенести на другой экран
//        binding.authLogout.setOnClickListener(v -> {
//            new UserStorageHandler(requireContext()).logout();
//            Navigation.findNavController(requireView()).navigate(com.example.finalprodproject.R.id.action_homeFragment_to_authFragment);
//        });

        return binding.getRoot();
    }
}
