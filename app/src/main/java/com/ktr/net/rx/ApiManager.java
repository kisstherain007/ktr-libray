//package com.ktr.net.rx;
//
//import com.ktr.net.rx.rxSimple.entity.WeatherData;
//
//import retrofit.Retrofit;
//import retrofit.http.GET;
//import retrofit.http.Query;
//import rx.Observable;
//import rx.Observer;
//import rx.Subscriber;
//import rx.Subscription;
//import rx.schedulers.Schedulers;
//import rx.subscriptions.Subscriptions;
//
///**
// * Created by kisstherain on 2015/12/16.
// */
//public class ApiManager {
//
//    private interface ApiManagerService{
//
//        @GET("/weather")
//        WeatherData getWeather(@Query("q") String place, @Query("units") String units);
//    }
//
////    private static final RestAdapter restAdapter = new RestAdapter.Builder()
////            .setServer("http://api.openweathermap.org/data/2.5")
////            .build();
//
//    Retrofit retrofit = new Retrofit.Builder()
//            .baseUrl("http://api.openweathermap.org/data/2.5")
//            .build();
//
//    private static final ApiManagerService apiManagerService = retrofit.create(ApiManagerService.class);
//
////    public static Observable<WeatherData> getWeatherData(final String city) {
////        return Observable.create(new Observable.OnSubscribeFunc<WeatherData>() {
////            @Override
////            public Subscription onSubscribe(Observer<? super WeatherData> observer) {
////                try {
////                    observer.onNext(apiManager.getWeather(city, "metric"));
////                    observer.onCompleted();
////                } catch (Exception e) {
////                    observer.onError(e);
////                }
////
////                return Subscriptions.empty();
////            }
////        }).subscribeOn(Schedulers.threadPoolForIO());
////    }
//
//    public static Observable<WeatherData> getWeatherData(final String city){
//
//        return Observable.create(new Observable.OnSubscribe<WeatherData>() {
//            @Override
//            public void call(Subscriber<? super WeatherData> subscriber) {
//                subscriber.onNext(apiManagerService.getWeather(city, "metric"));
//                subscriber.onCompleted();
//            }
//        });
//    }
//
//}
