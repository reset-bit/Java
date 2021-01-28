package com.example.a2_commoditybrowsing_x;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2_commoditybrowsing_x.entity.Food;

import java.util.ArrayList;
import java.util.List;

public class FoodFragment extends Fragment {
//    private final static String TAG = "FoodFragment";
    private boolean isTwoPage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_food, container, false);
        //加载组件
        RecyclerView foodRecyclerView = (RecyclerView) view.findViewById(R.id.food_RecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        foodRecyclerView.setLayoutManager(layoutManager);
        //加载适配器
        FoodAdapter adapter = new FoodAdapter(getFood());
        foodRecyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(getActivity().findViewById(R.id.twoPage_Layout) != null){
            isTwoPage = true;
        }
        else {
            isTwoPage = false;
        }
    }

    //内部类适配器
    class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder>{
        private List<Food> mFoodList;

        public FoodAdapter(List<Food> foodList){
            mFoodList = foodList;
        }

        class ViewHolder extends RecyclerView.ViewHolder{
            ImageView foodImage;
            TextView foodNameText;
            public ViewHolder(View view){
                super(view);
                foodImage = (ImageView) view.findViewById(R.id.food_Image);
                foodNameText = (TextView) view.findViewById(R.id.food_Name_Text);

//                Log.d(TAG, "this is viewholder creation");
            }
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            //加载recyclerview子项布局对应view!!!
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_food, parent, false);
            //获得要返回的viewholder对象
            final ViewHolder holder = new ViewHolder(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getAdapterPosition();//返回数据在Adapter中的位置，也许位置的变化还未来得及刷新到布局中
                    Food food = mFoodList.get(position);
                    if(position >= 13){//针对第二遍数据
                        position -= 13;
                    }
                    if(isTwoPage){
//                        Log.d(TAG, "双页模式");
                        //双页模式刷新detailsFragment中的内容
                        DetailFragment detailFragment = (DetailFragment) getFragmentManager().findFragmentById(R.id.detail_Fragment);
                        detailFragment.refresh(food.getName(), food.getDetail(), position);//直接调用碎片内函数实现消息通信，缺：耦合度太高（下同）
                    }
                    else{
//                        Log.d(TAG, "单页模式");
                        //单页模式启动detailsActivity，添加position参数获取数据位置
                        DetailActivity.actionStart(getActivity(), food.getName(), food.getDetail(), position);
                    }
                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Food food = mFoodList.get(position);
            holder.foodNameText.setText(food.getName());
            holder.foodImage.setImageResource(food.getImageId());
        }

        @Override
        public int getItemCount() {
            return mFoodList.size();
        }
    }

    private List<Food> getFood(){
        List<Food> foodList = new ArrayList<>();
        String detail;
        for(int i = 0; i < 2; i++){
            Food beef_stew_with_potatoes = new Food("土豆炖牛肉", R.drawable.beef_stew_with_potatoes_min);
            detail = getDetail(0);
            beef_stew_with_potatoes.setDetail(detail);
            foodList.add(beef_stew_with_potatoes);
            Food casserole = new Food("砂锅", R.drawable.casserole_min);
            detail = getDetail(1);
            casserole.setDetail(detail);
            foodList.add(casserole);
            Food dumplings = new Food("饺子", R.drawable.dumplings_min);
            detail = getDetail(2);
            dumplings.setDetail(detail);
            foodList.add(dumplings);
            Food gold_shrimp = new Food("黄金虾", R.drawable.gold_shrimp_min);
            detail = getDetail(3);
            gold_shrimp.setDetail(detail);
            foodList.add(gold_shrimp);
            Food laba_rice_porridge = new Food("腊八粥", R.drawable.laba_rice_porridge_min);
            detail = getDetail(4);
            laba_rice_porridge.setDetail(detail);
            foodList.add(laba_rice_porridge);
            Food moon_cake = new Food("月饼", R.drawable.moon_cake_min);
            detail = getDetail(5);
            moon_cake.setDetail(detail);
            foodList.add(moon_cake);
            Food mutton_hotpot = new Food("羊肉火锅", R.drawable.mutton_hotpot_min);
            detail = getDetail(6);
            mutton_hotpot.setDetail(detail);
            foodList.add(mutton_hotpot);
            Food mutton_soup = new Food("羊肉汤", R.drawable.mutton_soup_min);
            detail = getDetail(7);
            mutton_soup.setDetail(detail);
            foodList.add(mutton_soup);
            Food octopus_balls = new Food("章鱼小丸子", R.drawable.octopus_balls_min);
            detail = getDetail(8);
            octopus_balls.setDetail(detail);
            foodList.add(octopus_balls);
            Food rice_balls = new Food("饭团", R.drawable.rice_balls_min);
            detail = getDetail(9);
            rice_balls.setDetail(detail);
            foodList.add(rice_balls);
            Food sugar_fried_chestnut = new Food("糖炒栗子", R.drawable.sugar_fried_chestnut_min);
            detail = getDetail(10);
            sugar_fried_chestnut.setDetail(detail);
            foodList.add(sugar_fried_chestnut);
            Food vegetables = new Food("蔬菜", R.drawable.vegetables_min);
            detail = getDetail(11);
            vegetables.setDetail(detail);
            foodList.add(vegetables);
            Food zong_zi = new Food("粽子", R.drawable.zong_zi_min);
            detail = getDetail(12);
            zong_zi.setDetail(detail);
            foodList.add(zong_zi);
        }
        return foodList;
    }

    private String getDetail(int index){
        String d = "";
        switch (index){
            case 0:
                d = "1.新鲜牛腩用冷水浸泡两到三个小时中间换两次水\n" +
                        "2.葱切段，姜切丝，花椒备用\n" +
                        "3.锅中热油，油热加入葱姜花椒炒香，淋入酱油\n" +
                        "4.加入牛肉翻炒上色\n" +
                        "5.加入没过牛肉多一些得水和山楂三个，大火烧开、\n" +
                        "6.转小火炖煮一个小时四十分钟\n" +
                        "7.加入土豆和盐继续炖煮二十分钟\n" +
                        "8.美味的土豆炖牛肉就完成啦！";
                break;
            case 1:
                d = "1. 加一点花生油，等到锅热，加入姜蒜倒入豆瓣酱爆香\n" +
                        "2. 加入干辣椒，干花椒，加水（高汤最好）。大火煮开，加一点猪油\n" +
                        "3. 将熬好的汤倒入砂锅（没有砂锅就直接煮）\n" +
                        "4. 将材料切好，全部下锅\n" +
                        "5. 最后加一点花椒油、芝麻酱和豆腐乳，调味就可以啦";
                break;
            case 2:
                d = "【和面】多用冷水和面（30度以下的水）。余同饺子皮的和面方法。\n" +
                        "【做馅】水饺馅菜的水份不宜太大。不宜作水饺馅的菜：如西葫芦。但可作为蒸饺馅。\n" +
                        "【蒸】冒大气后20分钟可出锅。";
                break;
            case 3:
                d = "(1)将土司切成小方丁，放至干燥备用。\n" +
                        "(2)将虾仁、肥肉、荸荠剁碎后拌匀，再放入所有调味料一起拌匀成虾泥。\n" +
                        "(3)将虾泥捏成球状，沾上土司丁，依序完成所有虾球。\n" +
                        "(4)热油锅，以中火将虾球炸至金黄色后捞起，沥干油份即可。";
                break;
            case 4:
                d = "1.先将所有材料用水泡软，洗净，\n" +
                        "2.粥锅内注入水，加入所有材料煮开后，转中火煮约三十分钟，\n" +
                        "3.放入冰糖调味即可食用。";
                break;
            case 5:
                d = "1.大包酥酥皮制法：先将皮料调成面团。将油酥包入皮料，用滚筒面杖压成簿皮(0.67厘米)。卷成圆形条条，用刀切成10块，再将小坯的两端，沿切口处向里边折捏，用手掌揿扁成薄饼形，就可包馅。\n" +
                        "2.小包酥酥皮制法：面团和油酥面团制法同大包酥酥皮制法。将皮料与油酥料各分成10小块，将油酥逐一包入皮中，用面杖压扁后卷折成团，再用手掌揿扁成薄饼形即可包馅。\n" +
                        "3.制馅：根据配方拌匀，揉透滋润即可。下列馅需预制成半成品：\n" +
                        "(1)松子枣泥：先将黑枣去核、洗净、蒸烂绞成碎泥。糖放入锅内加水，加热溶化成糖浆，浓度以用竹筷能挑出丝为适度，然后将枣泥、油、松子加入，拌匀，烧到不粘手即可。\n" +
                        "(2)清水洗沙：赤豆9公斤，砂糖15公斤，饴糖1.5公斤，生油2.5公斤，水3公斤，制法与豆沙馅同。\n" +
                        "(3)猪油夹沙：所用的豆沙与清水洗沙制法相同。具体制法：将豆沙与糖、猪油丁、玫瑰花、桂花拌匀即可。\n" +
                        "4.包馅：先取豆沙馅揿薄置于酥皮上，再取猪油丁、桂花等混合料同时包入酥皮内。\n" +
                        "5.成型：包好馅后，在酥皮封口处贴上方型垫纸，压成1.67厘米厚的扁形月饼坯，每只90克，再在月饼生坯上盖以各种名称的印章。\n" +
                        "6.烘烤：月饼生坯推入炉内，炉温保持在240℃左右，待月饼上的花纹定型后适当降温，上下火要求一致，烤6～7分钟熟透即可出炉，待凉透后下盘。";
                break;
            case 6:
                d = "（1）姜蒜切片，葱切成“马耳朵”形，当归切成4毫米厚的片。\n" +
                        "（2）羊肉洗净，切成3厘米见方的块，入汤锅氽水捞起。\n" +
                        "（3）炒锅置火上，下油加热，放姜蒜片，葱，炒香，掺白汤，放羊肉，味精，鸡精，胡椒粉，料酒，当归，烧沸，除尽浮沫，倒入高压锅，压10分钟后起锅入盆，上台即可。";
                break;
            case 7:
                d = "（1）羊肉去皮，清除肥肉及筋膜，洗净切成片或丝\n" +
                        "（2）将羊肉、干地黄、归身、川断、怀牛膝、上北芪全部入锅，加水上火同煲约10小时，取浓汁，去渣留肉，再入正蜜糖，熬成麦芽糖样，即可食用。";
                break;
            case 8:
                d = "1. 将面粉、鸡蛋、水和高汤混合（为了防止变硬结块，水温和汤汁的温度最好要低。有的店甚至是用冰水混合的），面糊的粘度要适中。在超市就有卖混有高汤和磨好的山药“章鱼小丸子专用面粉”，用这种粉来做面糊，要搅拌到没有结块的状态就可以了。\n" +
                        "2. 将很多半圆凹槽的小丸子专用铁板放到煤气上加热，之后用毛刷刷上一层油，然后倒入面糊（刚好没过半圆凹槽）。\n" +
                        "3. 将章鱼块、天妇罗碎屑（油炸的屑屑头，可以不放）迅速撒到面糊里（要保证每个丸子里都要有章鱼块）。有的时候还会加进虾米、红生姜、葱段。\n" +
                        "4. 然后整个面上倒满面浆（如图），小心不要太过多，漏到下面。\n" +
                        "5. 当面糊有点变成膜状的时候，用一种像“千枚通し（SENMAIDOOSI，一种锥子）的专用工具翻面，让还没变硬的部分转到铁板的一侧加热。没有的用细竹签也可以，不要用牙签，因为太软，也太短，容易烫到手。\n" +
                        "6. 当一边烤好之后，再翻一面烤，使之成为一个球形。这里有一个关键，不能反复长时间地加热，不然里面就没有粘稠的口感了。\n" +
                        "7. 当烤成黄褐色并散发出香味后，就可以装盘了。外面比较硬，里面还有没有完全凝固，像奶油那种口感的小丸子，才是真的好小丸子~\n" +
                        "8. 涂上调味沙司。最好是用章鱼烧专用的烧酱。不然的话用海鲜酱加甜酱自制也可以。\n" +
                        "9. 撒上海苔末、美乃滋和木鱼花";
                break;
            case 9:
                d = "1、准备好食材，米饭最好晾凉。有条件的买海苔松会比较方便。\n" +
                        "2、混合所有食材，黄瓜不要放太多不然容易散架。\n" +
                        "3、戴上一次性手套，或者套个保鲜袋，捏成喜欢的形状，最后在底边包块海苔即可。";
                break;
            case 10:
                d = "1.板栗洗干净，放在砧板上，单手用刀剁个大约深5mm的口子，把栗子皮切破\n" +
                        "2.全部多好口子的栗子放进一个微波炉容器中，盖上盖子，把盖子上的透气孔打开，如微波炉高火叮2分钟\n" +
                        "3.取出打开盖子，这时候栗子已经开口了。放一小勺食用油和一小勺白糖，再撒点干桂花，盖上盖子上下摇晃，让食用油和白糖能均匀包裹在栗子表面\n" +
                        "4.盖子上的透气孔打开，放入微波炉再高火两分钟左右即可";
                break;
            case 11:
                d = "蔬菜需要加工才能够食用，更加有营养的加工烹饪方法如下：\n" +
                        "一、蔬菜应先洗后切，因为蔬菜中含有大量的维生素C，而维生素C又是水溶性维生素，很容易溶解于水中，如果把整个的菜放入水中清洗，然后再切，这样就可以减少维生素C和其他水溶性维生素的流失。\n" +
                        "二、蔬菜不宜用清洁剂清洗。\n" +
                        "三、一些带皮的蔬菜最好连皮一起吃，例如，茄子、萝卜等。因为皮中的维生素含量要比里面的肉含量高。\n" +
                        "四、在蔬菜烹调过程中，最好用大火去炒，因为蔬菜加热的时间越长，其中的营养素流失的就越多。例如水溶性维生素C、B族等。\n" +
                        "五、很多人在烹调蔬菜时喜欢放点碱面，其实这是不对的。因为碱可以破坏其中的维生素。如果加点果醋，恰恰可以起到保护维生素的作用。\n" +
                        "六、生吃蔬菜中的营养物质含量，不仅远远超过熟食，而且具有阻止上皮细胞发生恶变的作用，因此可以阻断致癌物质与宿主细胞的结合。";
                break;
            case 12:
                d = "1.调肉臊酱（肉馅加香菇、洋葱以及各种调料做成的酱）盛在沙锅内，放入鸡蛋，以小火卤20分钟，关火后任由其浸泡，食用时捞出即可。\n" +
                        "2.五花肉为红烧后的，香菇洗净用水浸泡，待用。\n" +
                        "3.炒锅内倒入花生油，烧热后放入少许洋葱末爆炒，然后加入酱油、精盐、味精、胡椒粉、葱、姜，加入适量清水，放入红烧五花肉，炒至汁收干。\n" +
                        "4.将糯米淘洗干净，用凉水浸泡约2小时；然后滤掉水，加盐、花生油、少许小苏打拌匀。\n" +
                        "5.将粽叶洗洗净，放入锅中，加水煮软捞起，沥水，备用。\n" +
                        "6.取3张粽叶，毛面相对，先放入1/3糯米，加入红烧五花肉、香菇，再放入2/3糯米包成三角形粽子，用干马兰草系好，即为粽子。\n" +
                        "7.将包好的粽子放入锅内，加入清水（水量要多，必须淹没粽子），用旺火煮约2小时，再用小火焖约3上时，即可食用。";
                break;
        }
        return d;
    }
}
