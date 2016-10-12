/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package breakingbad;

import java.awt.Image;
import java.util.ArrayList;


public class Animation{
        
        private ArrayList frames;
        private int index;
        private long time;
        private long length;

        public Animation(){
            frames = new ArrayList();
            length = 0;
            start();
        }

        public synchronized void addFrame(Image img, long len){
            length += len;
            frames.add(new animationFrame(img, length));
        }

        public synchronized void start(){
            time = 0;
            index = 0;
        }


        public synchronized void refresh(long elapsedTime){
            if (frames.size() > 1){      
                time += elapsedTime;    
                if (time >= length){
                    time = time % length;  
                    index = 0; 
                }
                while (time > getFrame(index).finalTime){
                    index++;
                }
            }
        }
        
        public synchronized void playOnce() {
            long timer = System.currentTimeMillis();
            index = 0;
            while(System.currentTimeMillis() < timer + 400) {
                index ++;
            }
            
        }

        public synchronized Image getImage(){
            if (frames.isEmpty())
                return null;
            return getFrame(index).image;
        }

        private animationFrame getFrame(int i){
            return (animationFrame)frames.get(i);
        }
        
        public int getIndex() {
            return index;
        }
        
        public void setIndex(int i) {
            index = i;
        }
        
        public class animationFrame{

            Image image;
            long finalTime;

            public animationFrame(){
                this.image = null;
                this.finalTime = 0;
            }

            public animationFrame(Image image, long finalTime){
                this.image = image;
                this.finalTime = finalTime;
            }

            public Image getImage(){
                return image;
            }

            public long getFinalTime(){
                return finalTime;
            }

            public void setImage (Image image){
                this.image = image;
            }

            public void setFinalTime(long finalTime){
                this.finalTime = finalTime;
            }
        }

        
    }


    