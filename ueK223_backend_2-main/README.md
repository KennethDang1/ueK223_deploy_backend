# uek223 Backend

## Setup
### 1. Clone Repository
Verwende diesen Link um das Repository zu clonen. Du kannst das Repository entweder über CMD oder GitHub Desktop clonen.
##### CMD
````
git clone https://github.com/KennethDang1/ueK223_backend_2.git
````
##### Github Desktop
1. Klicke oben auf den Button 'Add'
2. Wähle Clone Reposirory aus.
3. Kopiere den Link in das Feld URL
````
https://github.com/KennethDang1/ueK223_backend_2.git
````
### 2. Build Gradle
Klicke oben Rechts auf das Elefantensymbol wie unten auf dem Bild. Klicke auf den Build Folder und dann Doppelklick auf Build sie wird die ganze Umgebung aufgesetzt. 

![image](https://github.com/user-attachments/assets/132e006b-373a-4fc0-9d6e-06ac6b710027)

### 3. Start Backend
Um das Backend zu starten musst du wie oben auf dem Bild in den application Folder gehen und dort auf bootRun doppelklicken so startes du dein Backend

### 4. Create Docker 
Um einen Dockercontainer zu erstellen kannst du diesen Befehl verwenden. Um zu Kontrollieren ob der Container läuft kannst du Docker Desktop öffnen.

````
docker run --name postgres_db -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -p 5432:5432 -d postgres
````

![image](https://github.com/user-attachments/assets/ab6b8742-3e84-459c-bca0-c6bff366aa20)

### 5. Connect Docker with Backend
Um Docker mit dem Backend zu verbinden musst du auf das Datenbanksymbolklicken. 
![image](https://github.com/user-attachments/assets/0347b111-c536-459f-95a7-b81e72910a85)

Um die gewünschte Datenbank auszuwählen (in unserem Fall Postgres) kannst du auf das Plus Klicken.
![image](https://github.com/user-attachments/assets/643df9d9-3ae0-4bfc-9b80-9a9efedc2bd2)

Wenn du auf die Gewünschte Datenbank geklickt hast sollte ein Fenster aufgehen. Dort kannst du die Credentials für die Datenbank eingeben und eine Connection herstellen.

![image](https://github.com/user-attachments/assets/8c285439-4b6b-4201-a355-4165739e23c8)

Wenn die Connection erfolgreich war sollte es so aussehen.

![image](https://github.com/user-attachments/assets/5bc9d2b8-331f-4529-b2ca-e70790aef0fb)


### Swagger
Default Swagger Endpoint is http://localhost:8080/swagger-ui/index.html

### Troubleshooting

```
org.postgresql.util.PSQLException: ERROR: relation "role_authority" does not exist
```
Simply restart the application. Hibernate sometimes does not initialize the tables fast enough and causes this error. Restarting the application fixes this.
