# Fute objects in java :) 
Generierung von Zufallszahlen --> Ist die Zahl eine Primzahl? (aufteilung EINER Berechnung in mehrere Threads)


## Futre objects
- --> Hilfsmittel zur Erreichung von Multithreading 
Future<V< --> Interface
- Future Object -->
    - get --> V, Ergebnis holen 
        - Blockierender Aufruf
    - cancel(bool):bool
    - isCancelled():bool
    - isDone:bool 
    


Executor Service --> Verwaltung von einem Thread Pool
- Wiederverwendung von threads 
- Ausführung von Callable und Runnable 
- Methoen 
    - execute(Runnable r): void 
    - submit(Callable | Runnable) : Future<V>


Runnable --> Kein Rückgabewert. Runnable: void --> run()
Callable --> Wahrscheinlich doch ein Rückgabewert
- Callable: V --> call() (Nur über Executor Service ausführbar)
