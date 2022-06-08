package com.baec.vocabularyquiz.di;

import com.baec.vocabularyquiz.login.LoginViewModel;
import com.baec.vocabularyquiz.repository.UserRepository;
import com.baec.vocabularyquiz.repository.UserRepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {

    @Singleton
    @Provides
    public UserRepository provideUserRepository(){
        return new UserRepositoryImpl();
    }

    @Singleton
    @Provides
    public LoginViewModel provideLoginViewModel(UserRepository userRepository){
        return new LoginViewModel(userRepository);
    }

    @Singleton
    @Provides
    public String provideTestString(){
        return "This is an injected test string";
    }
}
