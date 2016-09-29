package org.digitalcampus.oppia.di;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import org.digitalcampus.oppia.application.DbHelper;
import org.digitalcampus.oppia.application.SessionManager;
import org.digitalcampus.oppia.exception.UserNotFoundException;
import org.digitalcampus.oppia.model.CompleteCourseProvider;
import org.digitalcampus.oppia.model.CoursesRepository;
import org.digitalcampus.oppia.model.Points;
import org.digitalcampus.oppia.model.User;

import java.util.ArrayList;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private Application app;

    public AppModule(Application app){
        this.app = app;
    }

    @Provides
    @Singleton
    public CoursesRepository provideCoursesRepository(){
        return new CoursesRepository();
    }

    @Provides
    @Singleton
    public CompleteCourseProvider provideCompleteCourseProvider(){
        return new CompleteCourseProvider();

    }

    @Provides
    @Singleton
    public SharedPreferences provideSharedPreferences(){
        return PreferenceManager.getDefaultSharedPreferences(app);
    }

    @Provides
    public User provideUser(){
        try {
            return DbHelper.getInstance(app).getUser(SessionManager.getUsername(app));
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Provides
    public ArrayList<Points> providePointsList(){
        return new ArrayList<>();
    }



}
