package br.com.timetotrade.search.di

import br.com.timetotrade.search.data.repository.SearchRepositoryImpl
import br.com.timetotrade.search.data.source.SearchDataSource
import br.com.timetotrade.search.data.source.SearchDataSourceImpl
import br.com.timetotrade.search.domain.SearchRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class DataModule {

    @Binds
    abstract fun bindSearchDataSource(
        dataSourceImpl: SearchDataSourceImpl
    ): SearchDataSource

    @Binds
    abstract fun bindSearchRepository(
        repositoryImpl: SearchRepositoryImpl
    ): SearchRepository
}
