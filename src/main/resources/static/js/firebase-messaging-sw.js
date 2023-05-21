// Scripts for firebase and firebase messaging
importScripts('https://www.gstatic.com/firebasejs/8.2.0/firebase-app.js');
importScripts('https://www.gstatic.com/firebasejs/8.2.0/firebase-messaging.js');

// Initialize the Firebase app in the service worker by passing the generated config
const firebaseConfig = {
    apiKey: "AIzaSyCt6-1wELxeIwLRDmMr5yF-wysX_rWSpMs",
    authDomain: "spring-firebase-fcm.firebaseapp.com",
    projectId: "spring-firebase-fcm",
    storageBucket: "spring-firebase-fcm.appspot.com",
    messagingSenderId: "645957630945",
    appId: "1:645957630945:web:afa125cb89bbf03e9670a0",
    measurementId: "G-W57VMWPQ16"
};

firebase.initializeApp(firebaseConfig);

// Retrieve firebase messaging
const messaging = firebase.messaging();

messaging.onBackgroundMessage(function(payload) {
  console.log('Received background message ', payload);
 // Customize notification here
  const notificationTitle = payload.notification.title;
  const notificationOptions = {
    body: payload.notification.body,
  };

  self.registration.showNotification(notificationTitle,
    notificationOptions);
});