# Notes 

## a: XML Datenschema
Jeden DT einmal verwenden + komplexer Datentyp 

Matr.Nr._name.xsd speichern 
556109_krueger.xsd

## b: 
XML Datei mit Verweis auf XSD  
- 5 Datenobjekte

## c:
XSD Validierung + Screenshot 

XML Dokument wohlgeformt?

## d:
JSON Datei erstellen und in Array speichern 
Matr.Nr._name.json speichern 
556109_krueger.json

## 2a
Parsing der XML mit Parsing in Objekte + Ausgabe 

--> Screenshot 

#XML 
<Persons>
    <Person name="Element" --> Attribut> 
        <Adress strasse="Sth" plz="17268"/>
    </Person> --> Element
    <Person> </Person>
</Persons>
!!! Nur ein Root-Objekt

#JSON
{"Persons": [{
    "Person1": "Sth", 
    "Person2": "Sth 2"
    }]
}

--> JSON Parsing: https://github.com/FasterXML/jackson
--> XML: https://github.com/javaee/jaxb-v2

### Buchung
- Flüge 
Flugnr.,Dep. - Des., Dep. Time, Des. Time
    - Passagiere
    Adresse, Geschlecht
    
<Buchung nr=""> 
    <Fluege>
    <Flug nr="">
            <Deperature></Departure>
            <Destination></Destination>
            <Dep. Date></Dep. Date>
            <Des. Date></Des. Date>
            <Dep. Time></Dep. Time>
            <Des. Time></Des. Time>
            <isSecureFlight>true</isSecureFlight>
            <Passagiere>
                <Passagier name="">
                    <adresse>ADRESSE</adresse>
                </Passagier>
            </Passagiere>
        </Flug>
    </Fluege>
    <preis>40.0</preis> --> Float
</Buchung>