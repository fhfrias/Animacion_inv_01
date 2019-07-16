package com.ouu.inv_traslation;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity{

    // The Context object.
    private Context context = null;

    // The root container layout in main activity layout xml file.
    private LinearLayout rootViewContainer = null;

    // This is scene one object.
    private Scene sceneOne = null;

    // This is scene two object.
    private Scene sceneTwo = null;

    // This is the transition object used to implement animation when display different scene.
    private Transition transition = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("dev2qa.com - Android Scene Transition Animation Example.");

        initControls();

        // Display scene one.
        sceneOne.enter();
    }


    // Initialize all view components.
    private void initControls()
    {
        if(context == null)
        {
            context = getApplicationContext();
        }

        if(rootViewContainer == null)
        {
            rootViewContainer = (LinearLayout)findViewById(R.id.root_linear_layout_container);
        }

        if(sceneOne == null)
        {
            // Get scene one from specified layout xml file with three parameters.
            sceneOne = Scene.getSceneForLayout(rootViewContainer, R.layout.scene_one, context);

            // When scene one is displayed in current activity screen.
            sceneOne.setEnterAction(new Runnable() {
                @Override
                public void run() {

                    /* Only when a scene is displayed in the activity, the view components in the scene layout
                     *  xml file can be accessed by code dynamically. */

                    // First get root view group of scene one layout.
                    ViewGroup viewGroup = sceneOne.getSceneRoot();

                    // Then find the button in the view group.
                    Button goToSceneTwoButton = viewGroup.findViewById(R.id.goto_scene_two_button);

                    // Set button onclick listener.
                    goToSceneTwoButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // When the button is clicked then show scene two in activity screen.
                            goToSceneTwo();
                        }
                    });

                }
            });

        }

        if(sceneTwo == null)
        {
            sceneTwo = Scene.getSceneForLayout(rootViewContainer, R.layout.scene_two, context);

            sceneTwo.setEnterAction(new Runnable() {
                @Override
                public void run() {
                    ViewGroup viewGroup = sceneTwo.getSceneRoot();

                    Button goToSceneOneButton = viewGroup.findViewById(R.id.goto_scene_one_button);

                    goToSceneOneButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            goToSceneOne();
                        }
                    });
                }
            });

        }

        if(transition == null)
        {
            // Get the transition inflater object.
            TransitionInflater transitionInflater = TransitionInflater.from(context);

            // Inflate transition settings from xml file.
            transition = transitionInflater.inflateTransition(R.transition.transitions);

        }
    }


    /* Display scene one with the specified transition animation. */
    private void goToSceneOne()
    {
        TransitionManager.go(sceneOne, transition);
    }

    /* Display scene two with the specified transition animation. */
    private void goToSceneTwo()
    {
        TransitionManager.go(sceneTwo, transition);
    }
}