/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.paulgray.bbrest.mixins;

import blackboard.base.BbList;
import blackboard.data.BbAttributes;
import blackboard.data.ExtendedData;
import blackboard.data.content.Content;
import blackboard.data.content.ContentFile;
import blackboard.data.content.ContentStatus;
import blackboard.data.course.Course;
import blackboard.data.gradebook.Lineitem;
import blackboard.data.gradebook.impl.Outcome;
import blackboard.data.gradebook.impl.OutcomeDefinition;
import blackboard.data.gradebook.impl.OutcomeDefinition.AggregationModel;
import blackboard.data.gradebook.impl.OutcomeDefinition.CalculationType;
import blackboard.data.gradebook.impl.OutcomeDefinitionCategory;
import blackboard.data.gradebook.impl.OutcomeDefinitionScale;
import blackboard.persist.Container;
import blackboard.persist.DataType;
import blackboard.persist.Id;
import blackboard.platform.gradebook2.GradableItem;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author paul
 */
@AutoMixin({Content.class, Id.class, Outcome.class, OutcomeDefinition.class, 
    OutcomeDefinitionCategory.class, Course.class, OutcomeDefinitionScale.class, Lineitem.class})
public abstract class BbMixins {

    @JsonIgnore
    public abstract ExtendedData getExtendedData();

    @JsonIgnore
    public abstract BbAttributes getBbAttributes();

    @JsonIgnore
    public abstract BbList<ContentFile> getContentFiles();

    @JsonIgnore
    public abstract GradableItem getGradebookItem();

    @JsonIgnore
    public abstract BbList<Id> getRemovedFiles();

    @JsonIgnore
    public abstract ContentStatus getContentStatus();

    @JsonIgnore
    public abstract boolean getHideAttempt();

    @JsonIgnore
    public abstract Outcome[] getOutcomes();

    @JsonIgnore
    public abstract Id getCategoryId();

    @JsonIgnore
    public abstract Container getContainer();

    @JsonIgnore
    public abstract DataType getDataType();
    
    @JsonIgnore
    public abstract Lineitem.AssessmentLocation getAssessmentLocation();
    
    @JsonIgnore
    public abstract AggregationModel getAggregationModel();
    
    @JsonIgnore 
    public abstract OutcomeDefinitionScale getScale();
    
    @JsonIgnore 
    public abstract CalculationType getCalculationType();
    
    @JsonIgnore 
    public abstract String getScoreProviderHandle();
}
