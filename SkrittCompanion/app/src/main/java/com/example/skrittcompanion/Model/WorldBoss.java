package com.example.skrittcompanion.Model;


import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "boss_table")
public class WorldBoss {

   @ColumnInfo(name = "closest_waypoint")
   private String closestWaypoint;

   @PrimaryKey
   @NonNull
   private String bossName;

   @ColumnInfo(name = "start_time_list")
   private ArrayList<String> startTime;

   @ColumnInfo(name = "background_image_id")
   private int backgroundId;

   @ColumnInfo(name = "area")
   private String area;

   public WorldBoss(String closestWaypoint, @NonNull String bossName, ArrayList<String> startTime, int backgroundId, String area) {
      this.closestWaypoint = closestWaypoint;
      this.bossName = bossName;
      this.backgroundId = backgroundId;
      this.startTime=startTime;
      this.area = area;
   }

   public String getClosestWaypoint() {
      return closestWaypoint;
   }

   public void setClosestWaypoint(String closestWaypoint) {
      this.closestWaypoint = closestWaypoint;
   }

   public String getBossName() {
      return bossName;
   }

   public void setBossName(String bossName) {
      this.bossName = bossName;
   }

   public ArrayList<String> getStartTime() {
      return startTime;
   }

   public void setStartTime(ArrayList<String> startTime) {
      this.startTime = startTime;
   }

   public int getBackgroundId() {
      return backgroundId;
   }

   public void setBackgroundId(int backgroundId) {
      this.backgroundId = backgroundId;
   }

   public String getArea() {
      return area;
   }

   public void setArea(String area) {
      this.area = area;
   }
}
