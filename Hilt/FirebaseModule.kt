package com.example.clienttest.UI.Hilt

import com.example.clienttest.UI.Contract.MainContract
import com.example.clienttest.UI.Repository.Repository
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Qualifier

@dagger.Module
@InstallIn(ViewModelComponent::class)
object FirebaseModule {
    @Provides
    @FBRepo
    fun providesFirebaseRepo():MainContract.FirebaseRepository{
        return Repository()
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
annotation class FBRepo