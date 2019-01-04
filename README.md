[![](https://jitpack.io/v/barisatalay/CoinAndBeyond.svg)](https://jitpack.io/#barisatalay/CoinAndBeyond)

# CoinAndBeyond
Turkish Bitcoin stock market library

# Market List: 
BtcTurk, Koineks, Paribu, SistemKoin

# Whos uses this library?
For now, no one :)

## Usage

### Step 1
```groovy
allprojects {
 repositories {
 ...
  maven { url 'https://jitpack.io' }
 }
}
```
### Step 2

Add dependencies in build.gradle.
```groovy
dependencies {
 implementation 'com.github.barisatalay:CoinAndBeyond:1.0.0'
}
```
### Step 3 (Use of library)
```kotlin
//For Paribu market
val myObject = CoinAndBeyond(Paribu(), this, this)
//For Koineks market
val myObject = CoinAndBeyond(Koineks(), this, this)
//For BtcTurk market
val myObject = CoinAndBeyond(BtcTurk(), this, this)
//For Sistem Koin market
val myObject = CoinAndBeyond(SistemKoin(), this, this)

//If you want to apply coin filter 
//If you do not use the following code, the whole list will appear.
myObject.setCoinFilter(arrayOf(enmCoin.BTC, enmCoin.XRP, etc.))

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
