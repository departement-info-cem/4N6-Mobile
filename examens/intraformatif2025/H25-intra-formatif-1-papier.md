# Intra formatif 4N6 : (1/2) partie papier

## Trace

**(4 points)** Produisez la trace d'exécution du code suivant :

```kotlin showLineNumbers
fun main() {
    var a = 100
    a += double(a) + (3 * triple(8))
    println(a)
}

fun double(x: Int): Int {
    val z = x * 2
    return z
}

fun triple(a: Int): Int {
    return a * 3
}
```

```




















```

## Mise en page

**(3 points)** Dessinez l'activité en mode portrait :

```xml showLineNumbers
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    tools:context=".MainActivity">

    <View
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_weight="1"
        android:background="#0000FF" />

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_weight="1"
        android:orientation="horizontal">

        <View
            android:layout_width="0dp"
            android:layout_height="match_parent"
            android:layout_weight="1"
            android:background="#FF0000" />

        <Button
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="3"
            android:layout_gravity="center_vertical"
            android:text="Pipo" />

    </LinearLayout>

</LinearLayout>
```

```
































```

## Retrofit Service

**(3 points)** On a un serveur qui attend une requête
- en méthode GET
- reçoit un nombre dans l'url par exemple **4**
- renvoie les **4** premiers nombres de la suite de Fibonacci
- par exemple pour l'URL `https://superserveur.ca/fibo/4` le serveur renvoie `[0, 1, 1, 2]`

Écris la **signature** de la méthode de service Retrofit pour cette requête:

```








```










