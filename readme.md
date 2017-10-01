## StickyNotes - Android client

### Description
Android client for sticky notes RESTful web service

### Features ANDROID
- consuming JSONs from RESTful web service

### More info & Source code WEB
github.com/twistezo/sticky-notes

### Build/Run - Android Virtual Device
```
1. Change Android SDK path in local.properties
2. gradlew build
3. Get VD name from AVD: 
   cd YOUR_PATH\Android\sdk\tools\bin\avdmanager list avd
4. cd..
5. Run AVD: 
   emulator -avd Nexus_5x_API_25
6. Drag'n'drop .apk file from 
   \app\build\outputs
   to your AVD
```

### Build/Run - Phone
```
1. Change Android SDK path in local.properties
2. gradlew build
3. Copy .apk file from \app\build\outputs to phone
```

### Screenshots
<table>
    <tr>
        <td>
            <img src="http://i.imgur.com/GcfTRLG.png" width="500">
        </td>
        <td>
            <img src="http://i.imgur.com/DioMC2v.png" width="500">
        </td>
    </tr>
    </tr>
</table>
