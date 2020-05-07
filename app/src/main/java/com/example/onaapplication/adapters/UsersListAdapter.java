package com.example.onaapplication.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onaapplication.R;
import com.example.onaapplication.models.OnaApiResponse;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UsersListAdapter extends RecyclerView.Adapter<UsersListAdapter.UsersListViewHolder>{
    private List<OnaApiResponse> mUsers;
    private List<OnaApiResponse> mFilteredUsers;
    private Context mContext;

    public UsersListAdapter(Context context, List<OnaApiResponse> users){
        mContext = context;
        mUsers = users;
    }
    @NonNull
    @Override
    public UsersListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.users_list_item, parent, false);
        UsersListViewHolder viewHolder = new UsersListViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UsersListViewHolder holder, int position) {
        holder.bindUsers(mUsers.get(position));

    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }



    public class UsersListViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.firstNameTextView) TextView mFirstNameTextView;
        @BindView(R.id.lastNameTextView) TextView mLastNameTextView;
        @BindView(R.id.idTextView) TextView mIdTextView;
        @BindView(R.id.userNameTextView) TextView mUserNameTextView;


        public UsersListViewHolder(@NonNull View itemView) {
            super(itemView);
            mContext = itemView.getContext();
            ButterKnife.bind(this, itemView);
        }

        @SuppressLint("SetTextI18n")
        public void bindUsers(OnaApiResponse user) {
            mFirstNameTextView.setText("First Name: "+user.getFirstName());
            mLastNameTextView.setText("Last Name: " +user.getLastName());
            mIdTextView.setText("ID: "+user.getId());
            mUserNameTextView.setText("Username: "+user.getUsername());
        }
    }
}
