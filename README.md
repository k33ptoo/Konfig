# Konfig
A Java Library from saving Frame Location, Size and Title.
## How to use
Simply add the dependency to your pom file

### Maven
```xml
<dependency>
  <groupId>com.k33ptoo</groupId>
  <artifactId>Konfig</artifactId>
  <version>1.0</version>
</dependency>
```

To save the settings use the code below inf your frame or class constructor.
```
com.k33ptoo.Konfig.saveKonfigurations(/*your frames or components*/);
```
Settings will be saved on window close and restored when the window is opened.

The following will be saved:

* **Size**
* **Location**
* **Title**

To clear configurations, call

```
com.k33ptoo.Konfig.clearKonfigurations();
```




