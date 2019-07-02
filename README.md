[![Android Arsenal]( https://img.shields.io/badge/Android%20Arsenal-CoinAndBeyond-green.svg?style=flat )]( https://android-arsenal.com/details/1/7753 )
[![](https://jitpack.io/v/barisatalay/CoinAndBeyond.svg)](https://jitpack.io/#barisatalay/CoinAndBeyond)
<a href="https://opensource.org/licenses/MIT"><img alt="License" src="https://img.shields.io/badge/License-MIT-blue.svg"/></a>
![](https://img.shields.io/github/languages/count/barisatalay/CoinAndBeyond.svg)
![](https://img.shields.io/github/repo-size/barisatalay/CoinAndBeyond.svg)
![](https://img.shields.io/github/last-commit/barisatalay/CoinAndBeyond.svg)
![](https://img.shields.io/github/followers/barisatalay.svg?style=social)
![](https://img.shields.io/github/stars/barisatalay/CoinAndBeyond.svg?style=social)


# CoinAndBeyond
Turkish Bitcoin stock market library
Android Bitcoin Library

# Market List: 
BtcTurk Api, Koineks Api, Paribu Api, SistemKoin Api

# Whos uses this library?
For now, no one :)

## Usage

### Step 1 (gradle.properties)
```groovy
....
....

android.useAndroidX=true
android.enableJetifier=true
```



### Step 2
```groovy
allprojects {
 repositories {
 ...
  maven { url 'https://jitpack.io' }
 }
}
```
### Step 3

Add dependencies in build.gradle.
```groovy
dependencies {
 implementation "androidx.appcompat:appcompat:1.0.2"
 implementation 'com.github.barisatalay:CoinAndBeyond:1.0.1'
}
```
### Step 4 (Use of library for AndroidX)
```kotlin
//For Paribu market
val myObject = CoinAndBeyond(Paribu(), this)
//For Koineks market
val myObject = CoinAndBeyond(Koineks(), this)
//For BtcTurk market
val myObject = CoinAndBeyond(BtcTurk(), this)
//For Sistem Koin market
val myObject = CoinAndBeyond(SistemKoin(), this)

//The coin list that will be returned from the "getCoins" method is filtered according to the list you provided here.
//NOTE: If you submit a blank list, they are all listed.
myObject.setCoinFilter(arrayOf(enmCoin.BTC, enmCoin.XRP, etc.))

//EN: Returns the coins published by the dataset(the Web Site) you are trying to use
myObject.getAvailableCoins()

myObject.getCoins(Observer {response->
  //use this object "response"
  ....
})

```


## Contact me
 If you have a better idea or way on this project, please let me know, thanks :)

[Email](mailto:b.atalay07@hotmail.com)

[My Blog](http://brsatalay.blogspot.com.tr)

[My Linkedin](http://linkedin.com/in/barisatalay07/)
