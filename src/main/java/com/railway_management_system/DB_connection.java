/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.railway_management_system;

import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Shamika Tissera
 */
public class DB_connection {
    public DB_connection(){
        establishConnection();
        initLog();
    }
    
    public void initLog(){
        try{    
           FileWriter fw=new FileWriter("testout.txt");    
           fw.write("Welcome to javaTpoint.");    
           fw.close();    
          }catch(Exception e){System.out.println(e);}
    }
    private Statement establishConnection(){
        
        String URL = "jdbc:mysql://localhost:3306/railway_management_system";
        String password = "";
        String user = "root";
        Statement statement;
        statement = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(URL, user, password);
            statement = connection.createStatement();
            
        }
        catch(SQLException e){
            System.err.println("Error when establishing connection... " + e);
        }
        catch(ClassNotFoundException e){
            System.err.println("Problem when fetching JDBC Driver " + e);
        }
        return statement;
    }
    
    protected boolean changeState(String username){
        try {
            String query = "UPDATE `passenger` SET `isPriorityPassenger` = '1' WHERE `passenger`.`username` = '" + username + "';";
            System.out.println(query);
            Statement statement = establishConnection();
            int rows = statement.executeUpdate(query);
            if (rows > 0) {
                Handle_Priority priority = new Handle_Priority();
                priority.occupyPriority(username);
                return true;
            }
            else{
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DB_connection.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    protected boolean registerAdmin(String id_no, String password, String uname, String lname, 
            String fname, String email){
        String tableRef = "insert into `administrator` (`id_no`, `password`, `f_name`, `l_name`, `email`)";
        String values = " values (\"" + id_no + "\", \"" + password + "\", " + "\"" 
                + fname + "\", \"" + lname + "\", \"" + email + "\" );";
        
        String tableRef_1 = "insert into `administrator_id_username`(`id_no`, `username`)";
        String values_2 = " values (\"" + id_no + "\", \"" + uname + "\");";
        
        try{
            Statement statement = establishConnection();
            System.err.println(tableRef + values);
            statement.executeUpdate(tableRef + values);
            System.err.println(tableRef_1 + values_2);
            statement.executeUpdate(tableRef_1 + values_2);
            return false;
        }
        catch(SQLException e){
            System.err.println("Administrator information storage error..." + e.getMessage());
            String feedback = e.getMessage().substring(0, 16);
            System.err.println(feedback);
            return true;
        }
    }
    
    protected boolean registerPassenger(String fname, String lname, String mobNo, String id, String email, 
        String uname, String password, boolean isPriority, double credit ){
        String tableRef = "insert into `railway_management_system`.`passenger`(`username`, `password`, "
                + "`email`, `mobile_no`, `credit_available`, `isPriorityPassenger`, `f_name`, `l_name`)";
        String values = "values (\"" + uname + "\", \"" + password + "\", \"" + email + "\", \"" + mobNo 
                + "\", " + credit +  ", " + isPriority + ", \"" + fname + "\", \"" + lname + "\");";
        
        String tableRef_1 = "insert into `passengerid-username`(`id_no`, `username`)";
        String values_2 = " values (\"" + id + "\", \"" + uname + "\");";
        
        try{
            Statement statement = establishConnection();
            System.err.println(tableRef + values);
            statement.executeUpdate(tableRef + values);
            System.err.println(tableRef_1 + values_2);
            statement.executeUpdate(tableRef_1 + values_2);
            
            if (isPriority) {
                Handle_Priority priority = new Handle_Priority();
                priority.occupyPriority(uname);
            }
            return false;
        }
        catch(SQLException e){
            System.err.println("Passenger information storage error..." + e.getMessage());
            String feedback = e.getMessage().substring(0, 16);
            System.err.println(feedback);
            return true;
        }
        
    }
    
    public boolean checkUsername(String username, String table){
        String query = "select `username` from " + table + " where `username` = \"" + username + "\"";
        try{
            Statement statement = establishConnection();
            System.out.println(query);
            ResultSet result = statement.executeQuery(query);
            result.next();
            String uname = result.getString("username");
            return true; //means username exists in the database
        }
        catch(SQLException e){
            System.err.println("Non-existent username... " + e.getMessage());
            return false;//means username does not exist in the database
        }
    }
    
    public boolean checkPassword(String password, String table){
        String query = "select `password` from " + table + " where `password` = \"" + password + "\"";
        try{
            Statement statement = establishConnection();
            System.out.println(query);
            ResultSet result = statement.executeQuery(query);
            result.next();
            String pass = result.getString("password");
            if(pass.equals(password)){
                return true;//means password exists in the database and is correct
            }
            else{
                System.err.println("Password incorrect... ");
                return false;//means password exists in the database but is incorrect
            }            
        }
        catch(SQLException e){
            System.err.println("Password incorrect..." + e.getMessage());
            return false;//means password does not exist in the database
        }
    }
    
    //Sajee
    
    class Handle_Route{
        
        public int insert_info(String routeID, String source, String destination, double generalPrice, double priorityPrice, double distance){
            int count = 0;
            try {
                String routeInsertSQLQuery = "INSERT INTO `route`(`route_id`, `source`, `destination`, `general_price`, `priority_price`, `distance`) "
                        + "VALUES(\"" + routeID + "\", \"" + source + "\", \"" + destination + "\", " + generalPrice + ", " + priorityPrice + ", " + distance + " )";
                Statement statement = establishConnection();
                count = statement.executeUpdate(routeInsertSQLQuery);
                
            } catch (SQLException ex) {
                Logger.getLogger(DB_connection.class.getName()).log(Level.SEVERE, null, ex);
            }
            return count;
        }
        
        public int updateRoute(String routeID, String source, String destination, double generalPrice, double priorityPrice, double distance){
            int count = 0;
            try {
                String updateSQLQuery = "UPDATE `route` SET `source` = \"" + source + "\", " + "`destination`= \"" + destination + "\", " + "`general_price`= " + generalPrice + ", `priority_price`= " + priorityPrice + ", `distance`= " + distance + " WHERE `route_id` = \"" + routeID + "\";";
                
                System.out.println(updateSQLQuery);
                
                Statement statement = establishConnection();
                count = statement.executeUpdate(updateSQLQuery);
                
                //PreparedStatement routePST = this.connx.prepareStatement(routeInsertSQLQuery);
            } catch (SQLException ex) {
                Logger.getLogger(DB_connection.class.getName()).log(Level.SEVERE, null, ex);
            }
            return count;
        }
        
        public int deleteRoute(String routeID){
            int count = 0;
            try {
                String deleteSQLQuery = "DELETE FROM `route` WHERE `route_id` = \"" + routeID + "\"";
                System.out.println(deleteSQLQuery);       
                Statement statement = establishConnection();
                count = statement.executeUpdate(deleteSQLQuery);
            } catch (SQLException ex) {
                Logger.getLogger(DB_connection.class.getName()).log(Level.SEVERE, null, ex);
            }
            return count;
            
        }
    }
    
    class Handle_Train{
        String TrainID; 
        int totalCapacity; 
        int availableCapacity;
        String routeID;
        int f_class;
        int s_class;
        int t_class;
        
        public Handle_Train(){}
        
        public Handle_Train(String TrainID, int totalCapacity, int availableCapacity, String routeID, int f_class, int s_class, int t_class){
            this.TrainID = TrainID;
            this.availableCapacity = availableCapacity;
            this.routeID = routeID;
            this.totalCapacity = totalCapacity;
            this.f_class = f_class;
            this.s_class = s_class;
            this.t_class = t_class;
        }
        
        protected int insertInfo(){
            try {
                String trainInsertSQLQuery = "INSERT INTO `train`(`train_id`, `total_capacity`, `available_capacity`, `1st_class`, `2nd_class`, `3rd_class`) ";
                String values = "values ('" + TrainID + "', " + totalCapacity + ", " + availableCapacity + ", " + f_class + ", " + s_class + ", " + t_class + ");";
                Statement statement = establishConnection();
                int rows = statement.executeUpdate(trainInsertSQLQuery + values);
                return rows;
            } catch (SQLException ex) {
                Logger.getLogger(DB_connection.class.getName()).log(Level.SEVERE, null, ex);
                return 0;
            }
        }
        
        public int updateInfo(){
            try {
                String updateSQLQuery = "UPDATE `train` SET `total_capacity` = '" + totalCapacity + "', `available_capacity` = '" + availableCapacity + "', `1st_class` = '" + f_class + "', `2nd_class` = '" + s_class + "', `3rd_class` = '" + t_class + "' WHERE `train`.`train_id` = '" + TrainID + "';";
                
                Statement statement = establishConnection();
                System.out.println(updateSQLQuery);
                int count = statement.executeUpdate(updateSQLQuery);
                return count;
            } catch (SQLException ex) {
                Logger.getLogger(DB_connection.class.getName()).log(Level.SEVERE, null, ex);
                return 0;
            }
        }
        
        public int deleteInfo(String Train_ID){
            try {
                String query = "DELETE FROM `train` WHERE `train`.`train_id` = \'" + Train_ID + "\'";
                System.out.println(query);
                Statement statement = establishConnection();
                int rows = statement.executeUpdate(query);
                return rows;
            } catch (SQLException ex) {
                Logger.getLogger(DB_connection.class.getName()).log(Level.SEVERE, null, ex);
                return 0;
            }
        }
    }
    
    class Make_Reservation{
        General_Ticket ticket;
        String route_id = null;
        String train_id = null;
        String timetable_id = null;
        String username = null;
        int capacity = 0;
        double price;
        String date;
        String source;
        String destination;
        
        public General_Ticket getTicket(){
            try {
                //get ticket_id
                //make sure username is defined in the passenger table
                System.out.println("date : " + date);
                String query = "SELECT ticket_id FROM `general_ticket` WHERE date = \"" + date + "\"";
                Statement statement = establishConnection();
                ResultSet rs = statement.executeQuery(query);
                rs.next();
                System.out.println("rs.getString(\"ticket_id\") : " + rs.getString("ticket_id"));
                String ticketID = rs.getString("ticket_id");
                ticket.ticket_id = ticketID;
                
            } catch (SQLException ex) {
                Logger.getLogger(DB_connection.class.getName()).log(Level.SEVERE, null, ex);
                
            }finally{
                return ticket;
                
            }          
            
        }
        
        private void updateDB(Ticket ticket){
            try {
                System.out.println("username : " + username);
                String query = "INSERT INTO `general_ticket` (`ticket_id`, `payment_method`, `source`, `destination`, `price`, `date`, `route_route_id`) VALUES (NULL, 'card', '" + source + "', '" + destination + "', '" + price + "', '" + date + "', '" + route_id + "');";
                Statement statement = establishConnection();
                System.out.println(query);
                statement.executeUpdate(query);
            } catch (SQLException ex) {
                Logger.getLogger(DB_connection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        private String getID(String username){          
            username = "shamika_as_passenger";
            try {
                               
                String query = "SELECT `id_no` FROM `passengerid-username` WHERE `username` = \"" + username + "\"";
                Statement statement = establishConnection();
                System.out.println(query);
                ResultSet rs = statement.executeQuery(query);
                rs.next();
                return rs.getString("id_no");
            } catch (SQLException ex) {
                Logger.getLogger(DB_connection.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }
        
        class Reservation_Info{
            String route_ID;
            String g_price;
        }
        
        public boolean createTuple(String username, String date, int count, String source, String destination, Calendar departure){
            try {
                String info_from_route_query = "SELECT general_price, route_id FROM railway_management_system.route where `source` = \"" + source + "\" and `destination` = \"" + destination + "\";";
                
                
                
                
                Statement statement = establishConnection();
                //get general price and route id
                System.out.println(info_from_route_query);
                ResultSet result = statement.executeQuery(info_from_route_query);
                result.next();
                price = result.getDouble("general_price");
                System.out.println(price);
                this.route_id = result.getString("route_id");
                System.out.println("route_id = " + route_id);
                
                //get train id
                String info_from_timetable_query = "SELECT DISTINCT train_id, time_slot_id FROM timetable where route_id = \"" + route_id + "\"";
                System.out.println(info_from_timetable_query);
                result = statement.executeQuery(info_from_timetable_query);
                result.next();
                train_id = result.getString("train_id");
                timetable_id = result.getString("time_slot_id");
                
                //get total capacity
                String info_from_train_query = "SELECT total_capacity FROM train where train_id = \"" + train_id + "\"";
                System.out.println(info_from_train_query);
                result = statement.executeQuery(info_from_train_query);
                result.next();
                capacity = result.getInt("total_capacity");
                String update_reservation_query = "INSERT INTO `reservation` (`date`, `capacity`, `bookings`) VALUES ('" + date + "', '" + capacity + "', '" + count + "');";
                //update timetable_time_slot_id
                System.out.println(update_reservation_query);
                statement.executeUpdate(update_reservation_query);
                return true; //operation successful
            } catch (SQLException ex) {
                Logger.getLogger(DB_connection.class.getName()).log(Level.SEVERE, null, ex);
                return false; //operation not successful
            }
        }
        
                
                public boolean reserve(String username, String date, int count, String source, String destination, Calendar departure){
                this.date = date;    
                this.destination = destination;
                this.username = username;
                this.source = source;
                String check_query = "select count(*) as \"total\" from reservation where date = \"" + date + "\";";
                String create_query = "insert into reservation(date, bookings) values (\"" + date + "\", " + count + ");";
                String update_query = "update reservation set `bookings` = `bookings` + " + count + " where date = \"" + date + "\";";
                
                try {
                    Statement statement = establishConnection();
                    //check whether there are any reservations existing for the given date
                    System.out.println(check_query);
                    ResultSet rs = statement.executeQuery(check_query);
                    rs.next();
                    int rows = rs.getInt("total");
                    System.out.println(rows);
                    if (rows == 0) {
                        boolean status = createTuple(username, date, count, source, destination, departure);
                        if (status) {
                            String id_no = getID(username);
                            General_Ticket ticket = new General_Ticket(id_no, route_id, date, source, destination, price);
                            updateDB(ticket);
                        }
                        return status;
                    }
                    else{
                        String get_capacity_and_count = "SELECT capacity, bookings FROM reservation WHERE date = \"" + date + "\"";
                        rs = statement.executeQuery(get_capacity_and_count);
                        rs.next();
                        int count_temp = rs.getInt("bookings");
                        
                        int capacity_temp = rs.getInt("capacity");
                        
                        if ((count_temp + count) > capacity_temp) {
                            System.out.println("count_temp = " + count_temp);
                            System.out.println("capacity_temp = " + capacity_temp);
                            System.out.println("count = " + count);
                            return false;
                        }
                        
                        System.out.println(update_query);
                        statement.executeUpdate(update_query);
                        String id_no = getID(username);
                        ticket = new General_Ticket(id_no, route_id, date, source, destination, price);
                        updateDB(ticket);
                        
                        
                        Reservation.ticketID = statement.executeQuery("SELECT ticket_id FROM `general_ticket` ORDER BY `ticket_id` DESC LIMIT 1;").getInt("ticket_id");
                        return true;
                    }
                    
                } catch (SQLException ex) {
                    System.err.println("Couldn't check query");
                    System.err.println(ex);
                    return false;
                }
                            
        }
                public void updateTrain(){
            try {
                String query = "UPDATE `train` SET available_capacity = total_capacity - (SELECT COUNT(*) FROM `priority_ticket`)";
                Statement statament = establishConnection();
                statament.executeUpdate(query);
            } catch (SQLException ex) {
                Logger.getLogger(DB_connection.class.getName()).log(Level.SEVERE, null, ex);
            }
                }
                
                public boolean checkPriorityConflict(String Source, String destination, int count){                    
            try {
                String info_from_route_query = "SELECT route_id FROM railway_management_system.route where `source` = \"" + source + "\" and `destination` = \"" + destination + "\";";
                Statement statement = establishConnection();
                ResultSet rs = statement.executeQuery(info_from_route_query);
                rs.next();
                String routeID = rs.getString("route_id");
                updateTrain();
                
                String query = "select train_id from `trains_in_a_given_route` where route_id = " + route_id + ";";
                rs = statement.executeQuery(query);
                rs.next();
                String trainID = rs.getString("train_id");
                
                String query0 = "SELECT available_capacity FROM `train` WHERE train_id = \"" + trainID + ";";
                rs = statement.executeQuery(query0);
                rs.next();
                int occupacity = rs.getInt("available_capacity");
                if (count < occupacity) {
                    return true;
                }
                else{
                    return false;
                }
            } catch (SQLException ex) {
                Logger.getLogger(DB_connection.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
                }
        
    }
    
    protected class Handle_Attendance{
        protected void beginSession(){
            try {
                String query = "INSERT INTO `attendance` (`ticket_id`) SELECT `ticket_id` FROM `priority_ticket`";
                Statement statement = establishConnection();
                statement.executeUpdate(query);
            } catch (SQLException ex) {
                Logger.getLogger(DB_connection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        protected boolean markAttendance(int ticket_id){
            try {
                String query = "UPDATE `attendance` SET attended = 1 WHERE ticket_id = " + ticket_id + ";";
                Statement statement = establishConnection();
                int rows = statement.executeUpdate(query);
                
                if (rows > 0) {
                    return true;
                }
                else{
                    return false;
                }
            } catch (SQLException ex) {
                Logger.getLogger(DB_connection.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
        
        protected void terminateSession(){
            try {
                String query = "TRUNCATE TABLE `attendance`";
                Statement statement = establishConnection();
                statement.executeUpdate(query);
            } catch (SQLException ex) {
                Logger.getLogger(DB_connection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        protected void updatePriorityTicket(){
            try{
                ArrayList<Integer> attended_ticket_id = new ArrayList<Integer>();
                String query = "select ticket_id from `attendance` where 'attended' = 0";
                Statement statement = establishConnection();
                ResultSet rs = statement.executeQuery(query);
                while(rs.next()){
                    attended_ticket_id.add(rs.getInt("ticket_id"));
                }
                try{
                    for (int i = 0; i < attended_ticket_id.size(); i++) {
                        String update_query = "UPDATE`priority_ticket` SET num_of_times_conditions_violated = num_of_times_conditions_violated + 1 WHERE ticket_id = " + attended_ticket_id.get(i) + ";";
                        statement.executeQuery(update_query);
                    }
                } catch(SQLException e){
                    System.err.println(e + "... Problem when updating priority_ticket");
                }                
                
            } catch(SQLException ex){
                Logger.getLogger(DB_connection.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }
        
        protected void removeUsersWhoCancelledReservations(){
            try {
                String query = "DELETE FROM `attendance` WHERE `ticket_id` = (SELECT `ticket_id` FROM `cancel_reservation`);";
                
                Statement statement = establishConnection();
                System.out.println(query);
                statement.executeUpdate(query);
            } catch (SQLException ex) {
                Logger.getLogger(DB_connection.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
    public class Handle_Record{
        String note;
        String username;
        String date;
        
        public Handle_Record(String note, String username, String date){
            this.date = date;
            this.note = note;
            this.username = username;
        }
        
        public int insertRecord(){
            try {
                String query = "INSERT INTO `manipulates` (`username`, `date`, `note`) VALUES ('" + username + "', '" + date + "', '" + note + "');";
                Statement statement = establishConnection();
                System.out.println(query);
                int count = statement.executeUpdate(query);
                return count;
            } catch (SQLException ex) {
                Logger.getLogger(DB_connection.class.getName()).log(Level.SEVERE, null, ex);
                return 0;
            }
        }
        
        
    }
    public class Priority_Reservation{
            String username;
            int ticket_id = -1;
            public Priority_Reservation(String username){
                this.username = username;
            }
            
            public boolean cancelReservation(){
                try {
                    //get ticket_id from `priority_ticket` table corresponding to the username
                    String query = "SELECT ticket_id FROM `priority_ticket` WHERE username = \"" + username + "\"";
                    Statement statement = establishConnection();
                    ResultSet rs = statement.executeQuery(query);
                    rs.next();
                    
                    ticket_id = rs.getInt("ticket_id");
                    if(ticket_id != -1){
                        return true;
                    }
                    else{
                        return false;
                    }
                } catch (SQLException ex) {
                    System.err.println("Not priority user...");
                    return false;
                }
            }
        }
    
    public class Handle_Timetable{
        String arrival;
        String departure;
        String timeSlot;
        String trainID;
        String routeID;
        
        public Handle_Timetable(String arrival, String departure, String timeSlot, String routeID){
            this.arrival = arrival;
            this.departure = departure;
            this.routeID = routeID;
            this.timeSlot = timeSlot;
            this.trainID = trainID;
        }
        
        public Handle_Timetable(String timeSlot){
            this.timeSlot = timeSlot;
        }
        
        public int insert(){
            try {
                String query = "INSERT INTO `timetable` (`time_slot_id`, `arrival`, `departure`, `train_id`, `route_id`) VALUES ('" + timeSlot + "', '" + arrival + "', '" + departure + "', '" + trainID + "', '" + routeID + "');";
                
                Statement statement = establishConnection();
                int rows = statement.executeUpdate(query);
                return rows;
            } catch (SQLException ex) {
                Logger.getLogger(DB_connection.class.getName()).log(Level.SEVERE, null, ex);
                return 0;
            }
        }
        
        public int delete(){
            try {
                String query = "DELETE FROM `timetable` WHERE `time_slot_id` = '" + timeSlot + ";";
                Statement statement = establishConnection();
                int rows = statement.executeUpdate(query);
                return rows;
            } catch (SQLException ex) {
                Logger.getLogger(DB_connection.class.getName()).log(Level.SEVERE, null, ex);
                return 0;
            }
        }
        
        public int update(){
            try {
                String query = "UPDATE `timetable` SET (`arrival` = '" + arrival + "', " + " `departure` = '" + departure + "', `train_id` = '" + trainID +  ", `route_id` = '" + routeID + "') WHERE `time_slot_id` = " + timeSlot + ";";
                
                Statement statement = establishConnection();
                int rows = statement.executeUpdate(query);
                return rows;
            } catch (SQLException ex) {
                Logger.getLogger(DB_connection.class.getName()).log(Level.SEVERE, null, ex);
                return 0;
            }
        }
    }
    
    public class Handle_Priority{
        public void occupyPriority(String username){
            try {
                Date Today = Calendar.getInstance().getTime();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String today = simpleDateFormat.format(Today);
                
                int month = Today.getMonth();
                int day = Today.getDay();
                int year = Today.getYear();
                
                String renewal = year + "-" + (month+1) + "-" + (day-1);
                
                String query = "INSERT INTO `priority_ticket` (`ticket_id`, `date`, `payment_method`, `num_of_times_conditions_violated`, `date_of_renewal`, `username`) VALUES (NULL, '" + today + "', 'card', '0', '" + renewal + "', '" + username + "');";
                Statement statement = establishConnection();
                
                statement.executeUpdate(query);
            } catch (SQLException ex) {
                Logger.getLogger(DB_connection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
