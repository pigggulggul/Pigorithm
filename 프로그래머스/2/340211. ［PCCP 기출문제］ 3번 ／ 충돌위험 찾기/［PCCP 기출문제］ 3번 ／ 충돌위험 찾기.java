import java.util.*;

class Point {
    int x;
    int y;
    public Point(int x,int y){
        this.x= x;
        this.y= y;
    }
}

class Solution {
    static int[][] plate = new int[101][101];
    static Point[] pos;
    static Point[] destination;
    static int[] order;
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        int count =0;
        pos = new Point[routes.length];
        destination = new Point[routes.length];
        order = new int[routes.length];
        
        // 1. routes에 있는 경로에서 각 첫번째가 시작 pos. 
        // 1-1. destination[]을 두고 각 routes의 도착 pos.
        for(int i = 0 ; i < routes.length ; i++){
            int start = routes[i][0];
            int end = routes[i][1];
            int startX = points[start-1][0];
            int startY = points[start-1][1];
            pos[i] = new Point(startX,startY);
            int endX = points[end-1][0];
            int endY = points[end-1][1];
            destination[i] = new Point(endX,endY);
            
            order[i] = 2;
            // System.out.println("시작 위치 : " + pos[i].x +" | "+pos[i].y);
            // System.out.println("도착지 위치 : " + destination[i].x + " | "+destination[i].y);
        }
        for(int i = 0 ; i < routes.length ; i++){
                if(pos[i].x==-1) continue;
                plate[pos[i].x][pos[i].y]++;
            }
            for(int i = 0 ; i < routes.length ; i++){
                if(pos[i].x==-1) continue;
                if(plate[pos[i].x][pos[i].y]>1){
                    answer++;
                }
                plate[pos[i].x][pos[i].y]=0;
        }
        
        while(true){
            for(int i = 0 ; i < routes.length; i++){
                //pos.x와 des.x가 맞지 않으면 일단 맞추기
                if(pos[i].x==-1){
                    continue;
                }
                if(pos[i].x != destination[i].x){
                    if(pos[i].x > destination[i].x){
                        pos[i].x-=1;
                    }else{
                        pos[i].x+=1;
                    }
                }else if(pos[i].y != destination[i].y){
                    if(pos[i].y > destination[i].y){
                        pos[i].y-=1;
                    }else{
                        pos[i].y+=1;
                    }
                }
            }
            // // System.out.println("이동 후");
            // for(int i = 0 ; i < routes.length; i++){
            //     System.out.println("시작 위치 : " + pos[i].x +" | "+pos[i].y);
            // System.out.println("도착지 위치 : " + destination[i].x + " | "+destination[i].y);
            // }
            
            // 겹치는 부분 체크
            for(int i = 0 ; i < routes.length ; i++){
                if(pos[i].x==-1) continue;
                plate[pos[i].x][pos[i].y]++;
            }
            for(int i = 0 ; i < routes.length ; i++){
                if(pos[i].x==-1) continue;
                if(plate[pos[i].x][pos[i].y]>1){
                    answer++;
                }
                plate[pos[i].x][pos[i].y]=0;
            }
            
            // 목적지 체크
            for(int i = 0 ; i < routes.length; i++){
                if(pos[i].x == destination[i].x && pos[i].y == destination[i].y){
                    // System.out.println("도착 : " + order[i]);
                    if(order[i] != routes[i].length){
                        int end = routes[i][order[i]];
                        int endX = points[end-1][0];
                        int endY = points[end-1][1];
                        destination[i] = new Point(endX,endY);
                        order[i]+=1;
                    }else{
                        // 끝까지 도달하면 -1
                        pos[i].x=-1;
                        pos[i].y=-1;
                    }
                }
            }   
            // 모든 pos가 다 x면 끝
            for(int i = 0 ; i < routes.length ;i++){
                if(pos[i].x==-1){
                    count++;
                }
            }
            if(count==routes.length){
                break;
            }else{
                count=0;
            }
        }  
        return answer;
    }
}