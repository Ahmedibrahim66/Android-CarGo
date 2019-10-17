package com.example.cargo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;

public class SliderAdapterCardBuyFull extends SliderViewAdapter<SliderAdapterCardBuyFull.SliderAdapterVH> {

    private Context context;
    private ArrayList<String> images = new ArrayList<>();
    int count;




    public SliderAdapterCardBuyFull(ArrayList<String> images , Context context ) {

        this.images = images;
        this.context = context;


    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.sliderbuycardfull, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, int position) {




        for (int i = 0 ; i<=position ; i++)
        {
            Glide.with(viewHolder.itemView)
                    .load(images.get(i))
                    .into(viewHolder.imageViewBackground);

        }



       /* switch (position) {
            case 0:
                Glide.with(viewHolder.itemView)
                        .load("https://scontent.fjrs1-1.fna.fbcdn.net/v/t1.0-9/70824633_2735156999862340_896265546171940864_n.jpg?_nc_cat=103&_nc_oc=AQmQVJOVA1fUI8H07vt6XnZP84gxz2IfJSYh2CCat7YaBC40VJ2bMui_5thF6z_ll_E&_nc_ht=scontent.fjrs1-1.fna&oh=c3130d5009feb7368384d25e31c6061c&oe=5E3B3A02")
                        .into(viewHolder.imageViewBackground);
                break;
            case 1:
                Glide.with(viewHolder.itemView)
                        .load("https://scontent.fjrs1-1.fna.fbcdn.net/v/t1.0-9/70425851_2735157256528981_2517890247606403072_n.jpg?_nc_cat=104&_nc_oc=AQk1U6WUh5mDo14iG2E-ce7KgoQBS2lDAuM5SPqRBHVyC11OQHmVKhEVmOxVedcpz84&_nc_ht=scontent.fjrs1-1.fna&oh=6c723571397857bb6c8d150982e9630e&oe=5E0232FC")
                        .into(viewHolder.imageViewBackground);
                break;
            case 2:
                Glide.with(viewHolder.itemView)
                        .load("https://scontent.fjrs1-1.fna.fbcdn.net/v/t1.0-9/70713968_2735157376528969_8269703612821667840_n.jpg?_nc_cat=111&_nc_oc=AQnmHqxfsV7GSsguOkRl0Cex8rYT9bgNSQ8EQ99USX5ojUwdRqms3dzKxifVGE35Btg&_nc_ht=scontent.fjrs1-1.fna&oh=2010c23952d5a3867185b722684ba23b&oe=5DFCDAE6")
                        .into(viewHolder.imageViewBackground);
                break;
            default:
                Glide.with(viewHolder.itemView)
                        .load("https://scontent.fjrs1-1.fna.fbcdn.net/v/t1.0-9/70847276_2735157456528961_331511796680622080_n.jpg?_nc_cat=105&_nc_oc=AQkYwu0uvxZBogSn7NT85x6cZhqa76HiMkOVk_Wn3kPqITAvsjJ0Brryr_29LAhW-Nc&_nc_ht=scontent.fjrs1-1.fna&oh=960f8868e06648692f7d1d85b6fa22a7&oe=5DF80268")
                        .into(viewHolder.imageViewBackground);
                break;

        }*/

    }

    @Override
    public int getCount() {
        //slider view count could be dynamic size
        int count = images.size();
        return count;
    }

    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView imageViewBackground;
        TextView textViewDescription;



        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.imageSlider);
            this.itemView = itemView;
        }
    }
}
