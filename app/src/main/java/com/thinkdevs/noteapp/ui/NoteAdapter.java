package com.thinkdevs.noteapp.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.thinkdevs.noteapp.EditorActivity;
import com.thinkdevs.noteapp.R;
import com.thinkdevs.noteapp.database.NoteEntity;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static com.thinkdevs.noteapp.utilities.Constants.NOTE_ID_KEY;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {
    private final List<NoteEntity> mNotes;
    private final Context mContext;

    public NoteAdapter(List<NoteEntity> mNotes, Context mContext) {
        this.mNotes = mNotes;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.note_list_item, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final NoteEntity note = mNotes.get(position);
        holder.mtextView.setText(note.getText());
        holder.mfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, EditorActivity.class);
                intent.putExtra(NOTE_ID_KEY, note.getId());
                mContext.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }

    //region Matching SUperclass
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView mtextView = itemView.findViewById(R.id.note_txt);
        FloatingActionButton mfab = itemView.findViewById(R.id.mfab);

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
    //endregion
}
