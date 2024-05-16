# Transaktionsanomalien

- **Dirty Read**: Eine Transaktion liest Daten, die von einer anderen Transaktion geändert, aber noch nicht bestätigt wurden.
- **Non-Repeatable Read**: Eine Transaktion liest denselben Datensatz zweimal, erhält jedoch unterschiedliche Werte, da eine andere Transaktion den Datensatz zwischen den Lesevorgängen geändert hat.
- **Phantom Read**: Eine Transaktion liest eine Reihe von Datensätzen, führt denselben Lesevorgang erneut aus und findet zusätzliche Datensätze, die von einer anderen Transaktion eingefügt wurden.
- **Serialization Anomaly**: Das Ergebnis mehrerer gleichzeitiger Transaktionen entspricht nicht dem Ergebnis, das durch sequenzielle Ausführung der Transaktionen erzielt würde. Dies umfasst alle oben genannten Anomalien.

## Generieren einer ID für neue Bestellungen

Beim Anlegen einer Bestellung wird zunächst die höchste vergebene Order-ID gesucht, und dann wird eine neue Bestellung entsprechend erhöht eingefügt. Demonstriert wird, wieso das zu Problemen führen kann. Anschließend werden diese beiden Operationen in eine Transaktion zusammengefasst. Es wird darüber diskutiert, ob das Problem durch Setzen eines Isolationsniveaus gelöst werden kann. Das Problem wird anschließend endgültig beseitigt, indem vor dem Auslesen der maximalen ID die Tabelle für alle anderen Teilnehmer gesperrt wird. Die Verwendung eines exklusiven Locks wird als die sinnvollste Art von Lock betrachtet.

Das ist ein Problem, wenn zwei Transaktionen gleichzeitig zugreifen, da beide Bestellungen dieselbe ID erhalten könnten.

Um sicherzustellen, dass keine anderen Transaktionen die maximale ID ändern können, bevor wir sie lesen, können wir die Tabelle mit einem exklusiven Lock sperren. Ein exklusiver Lock (auch bekannt als Schreib-Lock) verhindert, dass andere Transaktionen Schreiboperationen auf der Tabelle ausführen können, bis unser Lock freigegeben wird.

```java
conn.createStatement().executeUpdate("LOCK TABLE orders IN ROW EXCLUSIVE MODE");
```

## Atomizität bei Bestellungen

Tritt während dem Speichern der einzelnen Positionen einer Bestellung ein Fehler auf, so können unvollständige Bestellungen im System verbleiben oder auch Warenstände fälschlich verringert werden, was sicher ungewünscht ist. Demonstriert und dokumentiert wird dieses Verhalten. Diese Schritte werden daher nun in einer Transaktion durchgeführt, sodass sichergestellt ist, dass eine Bestellung ganz oder gar nicht erfasst wird. Nachdem das Anlegen in der Tabelle `orders` selbst nach Aufgabe 1 eine eigene Transaktion ist, wird im Fehlerfall eine "leere" Bestellung übrigbleiben - dies stellt hier kein Problem dar.

Beim Rollback bleibt im Fehlerfall eine Bestellung zurück. Dies kann jedoch vermieden werden, indem in derselben Transaktion die Bestellung gelöscht wird oder die Transaktion abgebrochen wird, wenn keine Elemente in der Bestellung enthalten sind.

## Anzeige von Statistiken

Der Aufruf von http://127.0.0.1:8000/stats liefert Statistiken über Bestellungen nach Ländern aufgeschlüsselt. Wird während dem Erstellen der Statistik eine Bestellung abgeschickt, so kann diese Statistik inkonsistent werden (z. B. in der Übersicht weniger Bestellungen anführen, als später in der Detailsansicht). Demonstriert wird dieses Verhalten, und danach wird sichergestellt, dass solche Phänomene ausgeschlossen werden können. Um die Performance nicht zu beeinträchtigen, soll die Lösung aber keine Locks verwenden, sondern durch Setzen eines entsprechenden Isolationsniveaus realisiert werden.

Um dieses Szenario zu demonstrieren und anschließend zu lösen, können folgende Schritte durchgeführt werden:

**Demonstration des Problems**:
- Es wird ein Prozess simuliert, der Statistiken über Bestellungen nach Ländern erstellt.
- Während dieses Prozesses wird gleichzeitig eine neue Bestellung abgeschickt, die verschiedene Werte ändert.

**Lösung durch Isolationsniveau**:
- Das Isolationsniveau für die Transaktionen, die die Statistiken erstellen, wird auf ein geeignetes Level gesetzt, um sicherzustellen, dass die Statistiken konsistent bleiben.
- Eine geeignete Option könnte das Isolationsniveau "Read Committed" sein, das sicherstellt, dass eine Transaktion nur auf bereits committed Daten anderer Transaktionen zugreift. Dadurch werden mögliche Inkonsistenzen vermieden, ohne die Performance stark zu beeinträchtigen.