package com.vkarmaedu.vkarma.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.vkarmaedu.vkarma.R;
import org.jetbrains.annotations.NotNull;

public class TeacherAssignment extends Fragment {

    public TeacherAssignment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_teacher_assignment, container, false);
    }

}
