# Android-Code-Exercies

#How we solve this problem:
 I first parse the json string and extract the shipments and driver lists. Then I iterate over each shipment and each driver and calculate the SS using the provided algorithm, and save the driver, shipment, and score in a list. Finally, I sort the list by score and print the driver, shipment, and score for each element of the list.

```
fun calculateSuitabilityScore(destinationStreetName: String, driverName: String): Double {
    var baseSS = 0.0
    if (destinationStreetName.length % 2 == 0) {
        baseSS = driverName.filter { it in "aeiouAEIOU" }.length * 1.5
    } else {
        baseSS = driverName.filter { it !in "aeiouAEIOU" }.length * 1.0
    }
    if (destinationStreetName.length % driverName.length != 1) {
        baseSS *= 1.5
    }
    return baseSS
}
```
