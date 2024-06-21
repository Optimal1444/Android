package com.example.clienttest.UI.Hilt

import android.app.Application
import android.content.Context
import com.example.clienttest.UI.Contract.MainContract
import com.example.clienttest.UI.Repository.SharedPreferencesRepository
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Qualifier


@dagger.Module
@InstallIn(ViewModelComponent::class)
object Module:Application() {
    @Provides
    @spRepo
    fun providesSPRepo(@ApplicationContext context:Context):MainContract.SharedPreferencesRepository{
        return SharedPreferencesRepository(context)
    }
}

@Qualifier
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER,
    AnnotationTarget.VALUE_PARAMETER,
    AnnotationTarget.FIELD
)
annotation class spRepo