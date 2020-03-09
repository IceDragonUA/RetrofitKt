package com.evaluation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.evaluation.model.project.Project;
import com.evaluation.retrofitkt.ICommand;
import com.evaluation.retrofitkt.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomListAdapter extends RecyclerView.Adapter<CustomListAdapter.ListAdapterHolder> {

    private final Context context;
    private final List<Project> projectList;
    private final ICommand<Project> clickCommand;


    public CustomListAdapter(Context context, List<Project> projectList, ICommand<Project> clickCommand) {
        this.context = context;
        this.projectList = projectList;
        this.clickCommand = clickCommand;
    }

    @Override
    public ListAdapterHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View rootView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        return new ListAdapterHolder(context, rootView);
    }

    @Override
    public void onBindViewHolder(ListAdapterHolder projectListAdapterHolder, int position) {
        Project projectItem = getItem(position);
        projectListAdapterHolder.bind(projectItem, clickCommand);
    }

    public Project getItem(int position) {
        return projectList.get(position);
    }

    @Override
    public int getItemCount() {
        return (null != projectList ? projectList.size() : 0);
    }

    static class ListAdapterHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title)
        TextView titleView;

        @BindView(R.id.thumbnail)
        ImageView thumbnailView;

        private Context mContext;

        public ListAdapterHolder(Context context, View view) {
            super(view);
            mContext = context;
            ButterKnife.bind(this, view);
        }

        public void bind(final Project selectedProject, final ICommand<Project> projectClickCommand) {

            titleView.setText(selectedProject.getProjectName());

            Glide.with(mContext)
                    .load(selectedProject.getProjectImage().getProjectImageUrl())
                    .into(thumbnailView);

            itemView.setOnClickListener(v -> projectClickCommand.execute(selectedProject));
        }
    }
}
