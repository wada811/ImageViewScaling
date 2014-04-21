package at.wada811.imageviewscaling;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import at.wada811.imageviewscaling.ParameterListFragment.ParameterDelegate;
import at.wada811.imageviewscaling.ParameterListFragment.ParameterDelegateProvider;

public class MainActivity extends ActionBarActivity implements ParameterDelegateProvider {

    final MainActivity self = this;

    private ImageViewFragment mScaleTypeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DrawerLayout drawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);
        drawerLayout.setScrimColor(0x00FFFFFF);
        getSupportFragmentManager().beginTransaction().add(R.id.drawer, ParameterListFragment.newInstance()).commit();

        mScaleTypeFragment = ImageViewFragment.newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, mScaleTypeFragment).commit();
    }

    @Override
    public ParameterDelegate getParameterDelegate(){
        return mScaleTypeFragment.getParameterDelegate();
    }

}
