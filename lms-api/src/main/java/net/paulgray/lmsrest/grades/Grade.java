/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.paulgray.lmsrest.grades;

import net.paulgray.lmsrest.course.Course;

/**
 *
 * @author paul
 */
public class Grade {
    
    public String id;
    public String title;
    public Course course;
    public Float score;
    public Float max;
    public String grade;
    
}
