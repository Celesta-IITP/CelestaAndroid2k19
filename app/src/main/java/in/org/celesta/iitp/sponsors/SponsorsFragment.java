package in.org.celesta.iitp.sponsors;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import in.org.celesta.iitp.R;

public class SponsorsFragment extends Fragment {

    public SponsorsFragment() {
    }

    private SponsorsAdapter adapter;
    private RecyclerView recyclerView;
    private View emptyView;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sponsors, container, false);

        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_sponsors);
        swipeRefreshLayout.setOnRefreshListener(this::updateData);

        recyclerView = view.findViewById(R.id.recycler_sponsors);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        adapter = new SponsorsAdapter(getContext());
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout.setRefreshing(true);
        updateData();

        return view;
    }

    private void updateData() {

        List<SponsorItem> sponsorItems = new ArrayList<>();
        sponsorItems.add(new SponsorItem("Google", "https://spacenews.com/wp-content/uploads/2018/05/24359364107_152b0152ff_k-879x485.jpg", "https://www.google.com"));
        sponsorItems.add(new SponsorItem("Google", "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png", "https://www.google.com"));
        sponsorItems.add(new SponsorItem("Google", "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png", "https://www.google.com"));
        adapter.setSponsorItemList(sponsorItems);

        swipeRefreshLayout.setRefreshing(false);
//        String token = PreferenceManager.getDefaultSharedPreferences(getContext()).getString(USER_TOKEN, "0");
//        Log.e("token", token);

//        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
//
//        Call<EventItem> call = service.getNewFeed(token, latest);
//        call.enqueue(new Callback<EventItem.FeedItemSuper1>() {
//            @Override
//            public void onResponse(Call<EventItem.FeedItemSuper1> call, Response<EventItem.FeedItemSuper1> response) {
//
//                if (response.isSuccessful()) {

//                    List<SponsorItem> allItems = response.body().getSponsors();
//
//                        adapter.setSponsorItemList(allItems);
//
//                }
//                swipeRefreshLayout.setRefreshing(false);
//            }
//
//            @Override
//            public void onFailure(Call<EventItem.FeedItemSuper1> call, Throwable t) {
//                Log.e("failure", t.getMessage());
//                swipeRefreshLayout.setRefreshing(false);
//            }
//        });


    }

}