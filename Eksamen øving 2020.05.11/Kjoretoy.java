import java.time.LocalDate;
public class Kjoretoy {
private int id;
private String modellnavn;
private int aarsmodell;
private int hestekrefter;
private LocalDate sistPaaService;
private String status;
public static final String STATUS_SERVICE = "Service";
public static final String STATUS_AKTIV = "Aktiv";
public Kjoretoy(int id, String modellnavn, int aarsmodell,
int hestekrefter, LocalDate sistPaaService, String status) {
this.id = id;
this.modellnavn = modellnavn;
this.aarsmodell = aarsmodell;
this.hestekrefter = hestekrefter;
this.sistPaaService = sistPaaService;
this.status = status;
}
@Override
public String toString() {
return id + ": " + modellnavn + ", "
+ hestekrefter + " hk (" + aarsmodell +"), siste service: "
+ sistPaaService + ", status=" + status;
}
public int getId() {
return id;
}
public void setId(int id) {
this.id = id;
}
public String getModellnavn() {
return modellnavn;
}
public void setModellnavn(String modellnavn) {
this.modellnavn = modellnavn;
}
public int getAarsmodell() {
return aarsmodell;
}
public void setAarsmodell(int aarsmodell) {
this.aarsmodell = aarsmodell;
}

public int getHestekrefter() {
return hestekrefter;
}
public void setHestekrefter(int hestekrefter) {
this.hestekrefter = hestekrefter;
}
public LocalDate getSistPaaService() {
return sistPaaService;
}
public String getStatus() {
return status;
}
public void setStatus(String status) {
this.status = status;
if (status == STATUS_SERVICE)
sistPaaService = LocalDate.now();
}
}