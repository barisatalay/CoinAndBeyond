package com.barisatalay.cointrackersample.di;

import com.barisatalay.cointrackersample.ui.viewmodel.CoinListViewModel;
import dagger.Subcomponent;

/**
 * A sub component to create ViewModels. It is called by the
 */
@Subcomponent
public interface ViewModelSubComponent {
    @Subcomponent.Builder
    interface Builder {
        ViewModelSubComponent build();
    }

    CoinListViewModel coinListViewModel();
}
