package com.github.istin.schedule;

import android.content.Intent;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.github.istin.schedule.gson.Lesson;
import com.github.istin.schedule.manager.ConfigurationManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends CommonHttpJobActivity<Lesson[]> {

    @Override
    protected int getContentViewLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected Class<Lesson[]> getResultClass() {
        return Lesson[].class;
    }

    @Override
    protected String getUrl() {
        final ConfigurationManager.Config config = ConfigurationManager.get(this).getConfig();
        return String.format(Api.SCHEDULE_LIST, config.getUniversity().getId(), config.getLecturer().getId());
    }

    @Override
    protected void applyResult(Lesson[] pLessons) {
        ListView listView = (ListView) findViewById(android.R.id.list);
        final ArrayAdapter<Lesson> adapter = new ArrayAdapter<Lesson>(this, R.layout.adapter_lesson, pLessons) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view;
                if (convertView == null) {
                    view = View.inflate(parent.getContext(), R.layout.adapter_lesson, null);
                } else {
                    view = convertView;
                }
                TextView textView1 = (TextView) view.findViewById(android.R.id.text1);
                TextView textView2 = (TextView) view.findViewById(android.R.id.text2);
                Lesson item = getItem(position);
                textView1.setText(item.getDate());
                textView2.setText(item.getTimeStart() + " - " + item.getTimeEnd() + "\n" + item.getTitle() + "\n" + item.getAddress() + " " + item.getRoom());

                return view;
            }

        };
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Lesson lesson = (Lesson) parent.getAdapter().getItem(position);
                Intent intent = new Intent(Intent.ACTION_EDIT);
                final String dateAsString = lesson.getDate() + " " + lesson.getTimeStart();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                try {
                    Date date = simpleDateFormat.parse(dateAsString);
                    //TODO change to constant type
                    intent.setType("vnd.android.cursor.item/event");
                    final long time = date.getTime();

                    //TODO change to constant columns from contract
                    intent.putExtra("beginTime", time);
                    //TODO parse from end time
                    intent.putExtra("endTime", time + 80 * 60 * 1000);
                    intent.putExtra("title", lesson.getTitle());
                    intent.putExtra("description", lesson.getAddress() + " " + lesson.getRoom());
                    intent.putExtra("eventLocation", lesson.getAddress() + " " + lesson.getRoom());

                    startActivity(intent);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            //TODO logic for settings
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
