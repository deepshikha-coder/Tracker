package deepshikha.jangidyahoo.tracker;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class recycleViewAdapter extends RecyclerView.Adapter<recycleViewAdapter.ViewHolder> {

    Context applicationContext;
    SQLiteDatabase database;
    Cursor cursor;
    int currentRowCount = -1;

    public recycleViewAdapter(Context applicationContext) {
        this.applicationContext = applicationContext;
        database = SQLiteDatabase.openOrCreateDatabase(    applicationContext.getFilesDir() + "locationDB", null,null);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
            cursor.moveToPosition(position);
            holder.longitude.setText(cursor.getString(cursor.getColumnIndex("Longitude")));
            holder.latitude.setText(cursor.getString(cursor.getColumnIndex("Latitude")));
            holder.date.setText(cursor.getString(cursor.getColumnIndex("Date")));
            holder.time.setText(cursor.getString(cursor.getColumnIndex("Time")));
            holder.number.setText(Integer.toString(position+1));
            holder.status.setText(cursor.getString(cursor.getColumnIndex("AppStatus")));
    }

    @Override
    public int getItemCount() {
        int count = (int)DatabaseUtils.queryNumEntries(database, "user_info");
        cursor = database.query("user_info", null, null, null, null, null, "date DESC, time DESC");
        currentRowCount = count;
        return count;
    }


    protected class ViewHolder extends RecyclerView.ViewHolder{
        TextView number;
         TextView longitude;
         TextView latitude;
         TextView time;
         TextView date;
         TextView status;
         public ViewHolder(View itemView) {
             super(itemView);
             status = itemView.findViewById(R.id.status);
             number = itemView.findViewById(R.id.number);
             longitude = itemView.findViewById(R.id.longitude);
             latitude = itemView.findViewById(R.id.latitude);
             date = itemView.findViewById(R.id.date);
             time = itemView.findViewById(R.id.time);

         }
     }
}
