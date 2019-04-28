package com.example.skrittcompanion.Model;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "table_boss")
public class WorldBoss {

   @ColumnInfo(name = "closest_waypoint")
   private String closestWaypoint;

   @PrimaryKey
   @NonNull
   private String bossName;

   @ColumnInfo(name = "initial_spawn_time_hour")
   private  int hour;

   @ColumnInfo(name = "initial_spawn_time_minute")
   private  int minute;

   @ColumnInfo(name = "wiki_page")
   private String wikiPage;

   @ColumnInfo(name = "area")
   private String area;

   @ColumnInfo(name="spawn_interval")
   private int interval;

   @ColumnInfo(name="expected_modulus")
   private int expected;

   public WorldBoss(String closestWaypoint, @NonNull String bossName, int hour, int minute, String wikiPage, String area, int interval) {
      this.closestWaypoint = closestWaypoint;
      this.bossName = bossName;
      this.hour = hour;
      this.minute = minute;
      this.wikiPage=wikiPage;
      this.area = area;
      this.interval = interval;
      expected=hour%interval;
   }

   public int getExpected() {
      return expected;
   }

   public void setExpected(int expected) {
      this.expected = expected;
   }

   public String getClosestWaypoint() {
      return closestWaypoint;
   }

   public void setClosestWaypoint(String closestWaypoint) {
      this.closestWaypoint = closestWaypoint;
   }

   @NonNull
   public String getBossName() {
      return bossName;
   }

   public void setBossName(@NonNull String bossName) {
      this.bossName = bossName;
   }

   public int getHour() {
      return hour;
   }

   public void setHour(int hour) {
      this.hour = hour;
   }

   public int getMinute() {
      return minute;
   }

   public void setMinute(int minute) {
      this.minute = minute;
   }


   public String getArea() {
      return area;
   }

   public void setArea(String area) {
      this.area = area;
   }

   public int getInterval() {
      return interval;
   }

   public void setInterval(int interval) {
      this.interval = interval;
   }

   public String getWikiPage() {
      return wikiPage;
   }

   public void setWikiPage(String wikiPage) {
      this.wikiPage = wikiPage;
   }
}
