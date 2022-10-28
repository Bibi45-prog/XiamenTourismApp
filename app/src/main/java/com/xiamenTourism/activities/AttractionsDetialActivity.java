package com.xiamenTourism.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiamenTourism.R;

public class AttractionsDetialActivity extends AppCompatActivity {
    TextView tv_attraction_name, tv_attraction_detail, tv_operating_hours, tv_address, tv_about, tv_operating, tv_address_text;
    String name;
    ImageView image_view_attraction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attractions_detial);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            name = extras.getString("name");

        }

        tv_attraction_name = findViewById(R.id.tv_attraction_name);
        tv_attraction_detail = findViewById(R.id.tv_attraction_detail);
        tv_operating_hours = findViewById(R.id.tv_operating_hours);
        tv_address = findViewById(R.id.tv_address);
        tv_about = findViewById(R.id.tv_about);
        tv_operating = findViewById(R.id.tv_operating);
        tv_address_text = findViewById(R.id.tv_address_name);
        image_view_attraction = findViewById(R.id.image_view_attraction);


        tv_about.setPaintFlags(tv_about.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        tv_operating.setPaintFlags(tv_operating.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        tv_address_text.setPaintFlags(tv_address_text.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        switch (name) {
            case "Gulangyu Island":

                tv_attraction_name.setText("Gulangyu Island");
                tv_attraction_detail.setText("Gulangyu is a century-old delicate small island on the southwestern side of Xiamen Island. With splendid natural scenery, colonial styled architectures and numerous interesting museums, Gulangyu Island is unmissable for your Xiamen Tour.");
                tv_operating_hours.setText("12:00 AM - 11:59 PM");
                tv_address.setText("Gulangyu, Siming District, Xiamen City");
                image_view_attraction.setImageDrawable(getResources().getDrawable(R.drawable.attractions_1));

                break;
            case "Xiamen Botanical Garden":
                tv_attraction_name.setText("Xiamen Botanical Garden");
                tv_attraction_detail.setText("Xiamen Botanical Garden is set amidst Wanshi Mountain in the southeastern part of Xiamen Island, also known as Wanshi Botanical Garden.. The garden is set on rolling hills and dotted with grotesque rocks, forming a dramatic rocky landscape");
                tv_operating_hours.setText("6:30am-6:00pm");
                tv_address.setText("No. 25, Huyuan Road, Siming District, Xiamen");
                image_view_attraction.setImageDrawable(getResources().getDrawable(R.drawable.attractions_2));


                break;
            case "Nanputuo Temple":
                tv_attraction_name.setText("Nanputuo Temple");
                tv_attraction_detail.setText("Nanputuo Temple is one of the most prosperous temples and one of the eight scenic spots in Xiamen. Facing the south and leaning the mountain, South Putuo Temple is built on a grand scale and imposing manner Architectures in the temple are very delicate.");
                tv_operating_hours.setText("Outer Gate: 3:00 – 20:00; Inner Gate: 3:00 – 18:00");
                tv_address.setText("No.515 Siming South Road, Siming District, Xiamen 361005, China");
                image_view_attraction.setImageDrawable(getResources().getDrawable(R.drawable.attractions_3));

                break;
            case "Xiamen University":
                tv_attraction_name.setText("Xiamen University");
                tv_attraction_detail.setText("Xiamen University (XMU), established in 1921 by renowned patriotic overseas Chinese leader Mr. Tan Kah Kee, is the first university founded by an overseas Chinese in the history of modern Chinese education. XMU has long been listed among China's leading universities on the national 211 Project, 985 Project and Double First-class initiative, which have been launched by the Chinese government to support selected universities in achieving world-class standing.");
                tv_operating_hours.setText("12:00 AM - 12:00 AM");
                tv_address.setText("No. 422, Siming South Road, Xiamen, Fujian, China. 361005");
                image_view_attraction.setImageDrawable(getResources().getDrawable(R.drawable.attractions_4));

                break;
            case "Kualagsu Huandao Road":
                tv_attraction_name.setText("Kualagsu Huandao Road");
                tv_attraction_detail.setText("Kulangsu Huandao Road is a path that runs along the coast of Xiamen. A day long trip along this road will open up many possibilities for many different activities. The cycle track is just a 100 metres from the beach. And there is a huge variety of bikes available. Where you decide to take a break, you will be welcomed by a great view and good food.");
                tv_operating_hours.setText("24 hours");
                tv_address.setText("Siming District, Xiamen 361000, China");
                image_view_attraction.setImageDrawable(getResources().getDrawable(R.drawable.attractions_5));

                break;
            case "Zhongshan Road Walking Street":
                tv_attraction_name.setText("Zhongshan Road Walking Street");
                tv_attraction_detail.setText("Zhongshan street is the nerve centre of Xiamen tourism. All tourists, travellers, visitors, how you may prefer to call them, they all come here at some point of their visit. It is said that more than a billion people pass here every year. More than a billion!");
                tv_operating_hours.setText("Mon-Sun: Open all day");
                tv_address.setText("Zhongshan Road, Siming District, Xiamen City");
                image_view_attraction.setImageDrawable(getResources().getDrawable(R.drawable.attractions_6));

                break;
            case "Xiamen Riegu Hot Spring Resort":
                tv_attraction_name.setText("Xiamen Riegu Hot Spring Resort");
                tv_attraction_detail.setText("Relaxing at a hot spring resort would give you keen enjoyment, especially in winter. Winter in Xiamen is not very cold. One can fend off the cold in winter by relaxing at a hot spring resort. There are two famous hot spring resorts with international standard in the city. One is Trithorn Hotspring Resort at Tong'an District and the other is Riyuegu Hotspring Resort at Haicang District. Both of them could provide 5-star service for guests. ");
                tv_operating_hours.setText("10:00 AM - 1:00 AM");
                tv_address.setText("1888 Fulian Road, Haishu District, Xiamen City");
                image_view_attraction.setImageDrawable(getResources().getDrawable(R.drawable.attractions_7));

                break;

        }


    }
}