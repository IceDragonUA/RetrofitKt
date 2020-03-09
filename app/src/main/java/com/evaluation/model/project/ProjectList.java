package com.evaluation.model.project;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProjectList {

    @SerializedName("projects")
    private List<Project> projectList;

    public ProjectList() {
    }

    public List<Project> getProjectList() {
        return projectList;
    }
}
