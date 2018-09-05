package mehdi.android.com.notapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TitleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int ALL_NOTE = 0;
    public static final int DELETE_NOTE = 1;

    List<MyNot> not = new ArrayList<>();
    public NotViewCallBack notViewCallBack;


    public TitleAdapter(NotViewCallBack notViewCallBack) {
        this.notViewCallBack = notViewCallBack;
    }

    public void addNot(MyNot myNot) {
        not.add(myNot);
        notifyItemInserted(not.size() + 1);
        checkDeleteAllNotesButtonState();

    }


    public void removeNot(int position) {
        this.not.remove(position);
        notifyItemRemoved(position);
        checkDeleteAllNotesButtonState();

    }

    public void updateNot(int position, String title, String writeBox) {
        not.get(position).setWriteBox(writeBox);
        not.get(position).setTitle(title);
        notifyItemChanged(position);
    }

    private void checkDeleteAllNotesButtonState() {
        notifyItemChanged(not.size());
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        if (viewType == ALL_NOTE) {

            View view = layoutInflater.inflate(R.layout.layout_item_adapter, parent, false);
            return new MyViewHolder(view, notViewCallBack);
        } else {

            View view = layoutInflater.inflate(R.layout.layout_item_delete, parent, false);
            return new DeleteNOtesViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof MyViewHolder) {
            ((MyViewHolder) holder).onBindNot(not.get(position));
        } else if (holder instanceof DeleteNOtesViewHolder) {
            ((DeleteNOtesViewHolder) holder).btnDeleteAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    notifyItemRangeRemoved(0, not.size());
                    not.clear();
                    ((DeleteNOtesViewHolder) holder).btnDeleteAll.setVisibility(View.GONE);


                }
            });
            ((DeleteNOtesViewHolder) holder).setVisibility(not.size() > 1 ? View.VISIBLE : View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return not.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        return position == not.size() ? DELETE_NOTE : ALL_NOTE;

    }



    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView editTextTitle;
        TextView writeTitle;
        ImageView edit;
        ImageView delete;
        NotViewCallBack notViewCallBack;

        public MyViewHolder(View itemView, final NotViewCallBack notViewCallBack) {
            super(itemView);
            this.notViewCallBack = notViewCallBack;
            editTextTitle = itemView.findViewById(R.id.et_item_title);
            writeTitle = itemView.findViewById(R.id.et_item_writeTitle);
            edit = itemView.findViewById(R.id.iv_item_edit);

            delete = itemView.findViewById(R.id.iv_item_delet);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    notViewCallBack.onRemovedButtonClicked(getAdapterPosition());
                }
            });

        }

        public void onBindNot(final MyNot not) {
            editTextTitle.setText(not.getTitle());
            writeTitle.setText(not.getWriteBox());
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    notViewCallBack.onEditButtonClicked(getAdapterPosition(), not);
                }
            });
        }
    }

    public static class DeleteNOtesViewHolder extends RecyclerView.ViewHolder {
        private View btnDeleteAll;

        public DeleteNOtesViewHolder(View itemView) {
            super(itemView);
            btnDeleteAll = itemView.findViewById(R.id.ll_deleteNotes_buttonDeleteAllNotes);
        }

        public void setVisibility(int visibility) {
            btnDeleteAll.setVisibility(visibility);
        }
    }

    public interface NotViewCallBack {
        void onEditButtonClicked(int position, MyNot myNot);

        void onRemovedButtonClicked(int position);

    }
}
