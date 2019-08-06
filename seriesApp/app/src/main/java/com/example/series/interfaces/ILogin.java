package com.example.series.interfaces;

public interface ILogin {

    interface model {
        //Interactor
        void getTokenApi();
    }

    interface presenter {
        //Interactor
        void getTokenApi();

        //View
        void showErrorApi(String error);

        //View
        void showTokenUser(String token);
    }

    interface view {
        //Presenter
        void showErrorApi(String error);

        //View
        void showTokenUser(String token);

        //Presenter
        void getTokenUser();
    }
}
