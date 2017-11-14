package com.lrx.Model.System;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TModuleExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table OBERACIIS_FUEL.T_MODULE
     *
     * @mbggenerated Thu May 14 11:49:07 CST 2015
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table OBERACIIS_FUEL.T_MODULE
     *
     * @mbggenerated Thu May 14 11:49:07 CST 2015
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table OBERACIIS_FUEL.T_MODULE
     *
     * @mbggenerated Thu May 14 11:49:07 CST 2015
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OBERACIIS_FUEL.T_MODULE
     *
     * @mbggenerated Thu May 14 11:49:07 CST 2015
     */
    public TModuleExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OBERACIIS_FUEL.T_MODULE
     *
     * @mbggenerated Thu May 14 11:49:07 CST 2015
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OBERACIIS_FUEL.T_MODULE
     *
     * @mbggenerated Thu May 14 11:49:07 CST 2015
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OBERACIIS_FUEL.T_MODULE
     *
     * @mbggenerated Thu May 14 11:49:07 CST 2015
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OBERACIIS_FUEL.T_MODULE
     *
     * @mbggenerated Thu May 14 11:49:07 CST 2015
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OBERACIIS_FUEL.T_MODULE
     *
     * @mbggenerated Thu May 14 11:49:07 CST 2015
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OBERACIIS_FUEL.T_MODULE
     *
     * @mbggenerated Thu May 14 11:49:07 CST 2015
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OBERACIIS_FUEL.T_MODULE
     *
     * @mbggenerated Thu May 14 11:49:07 CST 2015
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OBERACIIS_FUEL.T_MODULE
     *
     * @mbggenerated Thu May 14 11:49:07 CST 2015
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OBERACIIS_FUEL.T_MODULE
     *
     * @mbggenerated Thu May 14 11:49:07 CST 2015
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OBERACIIS_FUEL.T_MODULE
     *
     * @mbggenerated Thu May 14 11:49:07 CST 2015
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table OBERACIIS_FUEL.T_MODULE
     *
     * @mbggenerated Thu May 14 11:49:07 CST 2015
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("NAME is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("NAME is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("NAME =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("NAME <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("NAME >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("NAME >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("NAME <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("NAME <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("NAME like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("NAME not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("NAME in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("NAME not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("NAME between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("NAME not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("DESCRIPTION is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("DESCRIPTION is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("DESCRIPTION =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("DESCRIPTION <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("DESCRIPTION >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("DESCRIPTION >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("DESCRIPTION <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("DESCRIPTION <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("DESCRIPTION like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("DESCRIPTION not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("DESCRIPTION in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("DESCRIPTION not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("DESCRIPTION between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("DESCRIPTION not between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andVersionIsNull() {
            addCriterion("VERSION is null");
            return (Criteria) this;
        }

        public Criteria andVersionIsNotNull() {
            addCriterion("VERSION is not null");
            return (Criteria) this;
        }

        public Criteria andVersionEqualTo(String value) {
            addCriterion("VERSION =", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotEqualTo(String value) {
            addCriterion("VERSION <>", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThan(String value) {
            addCriterion("VERSION >", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThanOrEqualTo(String value) {
            addCriterion("VERSION >=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThan(String value) {
            addCriterion("VERSION <", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThanOrEqualTo(String value) {
            addCriterion("VERSION <=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLike(String value) {
            addCriterion("VERSION like", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotLike(String value) {
            addCriterion("VERSION not like", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionIn(List<String> values) {
            addCriterion("VERSION in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotIn(List<String> values) {
            addCriterion("VERSION not in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionBetween(String value1, String value2) {
            addCriterion("VERSION between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotBetween(String value1, String value2) {
            addCriterion("VERSION not between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andDisplayIndexIsNull() {
            addCriterion("DISPLAY_INDEX is null");
            return (Criteria) this;
        }

        public Criteria andDisplayIndexIsNotNull() {
            addCriterion("DISPLAY_INDEX is not null");
            return (Criteria) this;
        }

        public Criteria andDisplayIndexEqualTo(Long value) {
            addCriterion("DISPLAY_INDEX =", value, "displayIndex");
            return (Criteria) this;
        }

        public Criteria andDisplayIndexNotEqualTo(Long value) {
            addCriterion("DISPLAY_INDEX <>", value, "displayIndex");
            return (Criteria) this;
        }

        public Criteria andDisplayIndexGreaterThan(Long value) {
            addCriterion("DISPLAY_INDEX >", value, "displayIndex");
            return (Criteria) this;
        }

        public Criteria andDisplayIndexGreaterThanOrEqualTo(Long value) {
            addCriterion("DISPLAY_INDEX >=", value, "displayIndex");
            return (Criteria) this;
        }

        public Criteria andDisplayIndexLessThan(Long value) {
            addCriterion("DISPLAY_INDEX <", value, "displayIndex");
            return (Criteria) this;
        }

        public Criteria andDisplayIndexLessThanOrEqualTo(Long value) {
            addCriterion("DISPLAY_INDEX <=", value, "displayIndex");
            return (Criteria) this;
        }

        public Criteria andDisplayIndexIn(List<Long> values) {
            addCriterion("DISPLAY_INDEX in", values, "displayIndex");
            return (Criteria) this;
        }

        public Criteria andDisplayIndexNotIn(List<Long> values) {
            addCriterion("DISPLAY_INDEX not in", values, "displayIndex");
            return (Criteria) this;
        }

        public Criteria andDisplayIndexBetween(Long value1, Long value2) {
            addCriterion("DISPLAY_INDEX between", value1, value2, "displayIndex");
            return (Criteria) this;
        }

        public Criteria andDisplayIndexNotBetween(Long value1, Long value2) {
            addCriterion("DISPLAY_INDEX not between", value1, value2, "displayIndex");
            return (Criteria) this;
        }

        public Criteria andModuleUrlIsNull() {
            addCriterion("MODULE_URL is null");
            return (Criteria) this;
        }

        public Criteria andModuleUrlIsNotNull() {
            addCriterion("MODULE_URL is not null");
            return (Criteria) this;
        }

        public Criteria andModuleUrlEqualTo(String value) {
            addCriterion("MODULE_URL =", value, "moduleUrl");
            return (Criteria) this;
        }

        public Criteria andModuleUrlNotEqualTo(String value) {
            addCriterion("MODULE_URL <>", value, "moduleUrl");
            return (Criteria) this;
        }

        public Criteria andModuleUrlGreaterThan(String value) {
            addCriterion("MODULE_URL >", value, "moduleUrl");
            return (Criteria) this;
        }

        public Criteria andModuleUrlGreaterThanOrEqualTo(String value) {
            addCriterion("MODULE_URL >=", value, "moduleUrl");
            return (Criteria) this;
        }

        public Criteria andModuleUrlLessThan(String value) {
            addCriterion("MODULE_URL <", value, "moduleUrl");
            return (Criteria) this;
        }

        public Criteria andModuleUrlLessThanOrEqualTo(String value) {
            addCriterion("MODULE_URL <=", value, "moduleUrl");
            return (Criteria) this;
        }

        public Criteria andModuleUrlLike(String value) {
            addCriterion("MODULE_URL like", value, "moduleUrl");
            return (Criteria) this;
        }

        public Criteria andModuleUrlNotLike(String value) {
            addCriterion("MODULE_URL not like", value, "moduleUrl");
            return (Criteria) this;
        }

        public Criteria andModuleUrlIn(List<String> values) {
            addCriterion("MODULE_URL in", values, "moduleUrl");
            return (Criteria) this;
        }

        public Criteria andModuleUrlNotIn(List<String> values) {
            addCriterion("MODULE_URL not in", values, "moduleUrl");
            return (Criteria) this;
        }

        public Criteria andModuleUrlBetween(String value1, String value2) {
            addCriterion("MODULE_URL between", value1, value2, "moduleUrl");
            return (Criteria) this;
        }

        public Criteria andModuleUrlNotBetween(String value1, String value2) {
            addCriterion("MODULE_URL not between", value1, value2, "moduleUrl");
            return (Criteria) this;
        }

        public Criteria andIconIsNull() {
            addCriterion("ICON is null");
            return (Criteria) this;
        }

        public Criteria andIconIsNotNull() {
            addCriterion("ICON is not null");
            return (Criteria) this;
        }

        public Criteria andIconEqualTo(String value) {
            addCriterion("ICON =", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconNotEqualTo(String value) {
            addCriterion("ICON <>", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconGreaterThan(String value) {
            addCriterion("ICON >", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconGreaterThanOrEqualTo(String value) {
            addCriterion("ICON >=", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconLessThan(String value) {
            addCriterion("ICON <", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconLessThanOrEqualTo(String value) {
            addCriterion("ICON <=", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconLike(String value) {
            addCriterion("ICON like", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconNotLike(String value) {
            addCriterion("ICON not like", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconIn(List<String> values) {
            addCriterion("ICON in", values, "icon");
            return (Criteria) this;
        }

        public Criteria andIconNotIn(List<String> values) {
            addCriterion("ICON not in", values, "icon");
            return (Criteria) this;
        }

        public Criteria andIconBetween(String value1, String value2) {
            addCriterion("ICON between", value1, value2, "icon");
            return (Criteria) this;
        }

        public Criteria andIconNotBetween(String value1, String value2) {
            addCriterion("ICON not between", value1, value2, "icon");
            return (Criteria) this;
        }

        public Criteria andPublishedIsNull() {
            addCriterion("PUBLISHED is null");
            return (Criteria) this;
        }

        public Criteria andPublishedIsNotNull() {
            addCriterion("PUBLISHED is not null");
            return (Criteria) this;
        }

        public Criteria andPublishedEqualTo(Date value) {
            addCriterion("PUBLISHED =", value, "published");
            return (Criteria) this;
        }

        public Criteria andPublishedNotEqualTo(Date value) {
            addCriterion("PUBLISHED <>", value, "published");
            return (Criteria) this;
        }

        public Criteria andPublishedGreaterThan(Date value) {
            addCriterion("PUBLISHED >", value, "published");
            return (Criteria) this;
        }

        public Criteria andPublishedGreaterThanOrEqualTo(Date value) {
            addCriterion("PUBLISHED >=", value, "published");
            return (Criteria) this;
        }

        public Criteria andPublishedLessThan(Date value) {
            addCriterion("PUBLISHED <", value, "published");
            return (Criteria) this;
        }

        public Criteria andPublishedLessThanOrEqualTo(Date value) {
            addCriterion("PUBLISHED <=", value, "published");
            return (Criteria) this;
        }

        public Criteria andPublishedIn(List<Date> values) {
            addCriterion("PUBLISHED in", values, "published");
            return (Criteria) this;
        }

        public Criteria andPublishedNotIn(List<Date> values) {
            addCriterion("PUBLISHED not in", values, "published");
            return (Criteria) this;
        }

        public Criteria andPublishedBetween(Date value1, Date value2) {
            addCriterion("PUBLISHED between", value1, value2, "published");
            return (Criteria) this;
        }

        public Criteria andPublishedNotBetween(Date value1, Date value2) {
            addCriterion("PUBLISHED not between", value1, value2, "published");
            return (Criteria) this;
        }

        public Criteria andReleaseUserIdIsNull() {
            addCriterion("RELEASE_USER_ID is null");
            return (Criteria) this;
        }

        public Criteria andReleaseUserIdIsNotNull() {
            addCriterion("RELEASE_USER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andReleaseUserIdEqualTo(String value) {
            addCriterion("RELEASE_USER_ID =", value, "releaseUserId");
            return (Criteria) this;
        }

        public Criteria andReleaseUserIdNotEqualTo(String value) {
            addCriterion("RELEASE_USER_ID <>", value, "releaseUserId");
            return (Criteria) this;
        }

        public Criteria andReleaseUserIdGreaterThan(String value) {
            addCriterion("RELEASE_USER_ID >", value, "releaseUserId");
            return (Criteria) this;
        }

        public Criteria andReleaseUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("RELEASE_USER_ID >=", value, "releaseUserId");
            return (Criteria) this;
        }

        public Criteria andReleaseUserIdLessThan(String value) {
            addCriterion("RELEASE_USER_ID <", value, "releaseUserId");
            return (Criteria) this;
        }

        public Criteria andReleaseUserIdLessThanOrEqualTo(String value) {
            addCriterion("RELEASE_USER_ID <=", value, "releaseUserId");
            return (Criteria) this;
        }

        public Criteria andReleaseUserIdLike(String value) {
            addCriterion("RELEASE_USER_ID like", value, "releaseUserId");
            return (Criteria) this;
        }

        public Criteria andReleaseUserIdNotLike(String value) {
            addCriterion("RELEASE_USER_ID not like", value, "releaseUserId");
            return (Criteria) this;
        }

        public Criteria andReleaseUserIdIn(List<String> values) {
            addCriterion("RELEASE_USER_ID in", values, "releaseUserId");
            return (Criteria) this;
        }

        public Criteria andReleaseUserIdNotIn(List<String> values) {
            addCriterion("RELEASE_USER_ID not in", values, "releaseUserId");
            return (Criteria) this;
        }

        public Criteria andReleaseUserIdBetween(String value1, String value2) {
            addCriterion("RELEASE_USER_ID between", value1, value2, "releaseUserId");
            return (Criteria) this;
        }

        public Criteria andReleaseUserIdNotBetween(String value1, String value2) {
            addCriterion("RELEASE_USER_ID not between", value1, value2, "releaseUserId");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNull() {
            addCriterion("PARENT_ID is null");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNotNull() {
            addCriterion("PARENT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andParentIdEqualTo(Long value) {
            addCriterion("PARENT_ID =", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotEqualTo(Long value) {
            addCriterion("PARENT_ID <>", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThan(Long value) {
            addCriterion("PARENT_ID >", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThanOrEqualTo(Long value) {
            addCriterion("PARENT_ID >=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThan(Long value) {
            addCriterion("PARENT_ID <", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThanOrEqualTo(Long value) {
            addCriterion("PARENT_ID <=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdIn(List<Long> values) {
            addCriterion("PARENT_ID in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotIn(List<Long> values) {
            addCriterion("PARENT_ID not in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdBetween(Long value1, Long value2) {
            addCriterion("PARENT_ID between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotBetween(Long value1, Long value2) {
            addCriterion("PARENT_ID not between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("TYPE is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Long value) {
            addCriterion("TYPE =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Long value) {
            addCriterion("TYPE <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Long value) {
            addCriterion("TYPE >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Long value) {
            addCriterion("TYPE >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Long value) {
            addCriterion("TYPE <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Long value) {
            addCriterion("TYPE <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Long> values) {
            addCriterion("TYPE in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Long> values) {
            addCriterion("TYPE not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Long value1, Long value2) {
            addCriterion("TYPE between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Long value1, Long value2) {
            addCriterion("TYPE not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andWorkflowKeyIsNull() {
            addCriterion("WORKFLOW_KEY is null");
            return (Criteria) this;
        }

        public Criteria andWorkflowKeyIsNotNull() {
            addCriterion("WORKFLOW_KEY is not null");
            return (Criteria) this;
        }

        public Criteria andWorkflowKeyEqualTo(String value) {
            addCriterion("WORKFLOW_KEY =", value, "workflowKey");
            return (Criteria) this;
        }

        public Criteria andWorkflowKeyNotEqualTo(String value) {
            addCriterion("WORKFLOW_KEY <>", value, "workflowKey");
            return (Criteria) this;
        }

        public Criteria andWorkflowKeyGreaterThan(String value) {
            addCriterion("WORKFLOW_KEY >", value, "workflowKey");
            return (Criteria) this;
        }

        public Criteria andWorkflowKeyGreaterThanOrEqualTo(String value) {
            addCriterion("WORKFLOW_KEY >=", value, "workflowKey");
            return (Criteria) this;
        }

        public Criteria andWorkflowKeyLessThan(String value) {
            addCriterion("WORKFLOW_KEY <", value, "workflowKey");
            return (Criteria) this;
        }

        public Criteria andWorkflowKeyLessThanOrEqualTo(String value) {
            addCriterion("WORKFLOW_KEY <=", value, "workflowKey");
            return (Criteria) this;
        }

        public Criteria andWorkflowKeyLike(String value) {
            addCriterion("WORKFLOW_KEY like", value, "workflowKey");
            return (Criteria) this;
        }

        public Criteria andWorkflowKeyNotLike(String value) {
            addCriterion("WORKFLOW_KEY not like", value, "workflowKey");
            return (Criteria) this;
        }

        public Criteria andWorkflowKeyIn(List<String> values) {
            addCriterion("WORKFLOW_KEY in", values, "workflowKey");
            return (Criteria) this;
        }

        public Criteria andWorkflowKeyNotIn(List<String> values) {
            addCriterion("WORKFLOW_KEY not in", values, "workflowKey");
            return (Criteria) this;
        }

        public Criteria andWorkflowKeyBetween(String value1, String value2) {
            addCriterion("WORKFLOW_KEY between", value1, value2, "workflowKey");
            return (Criteria) this;
        }

        public Criteria andWorkflowKeyNotBetween(String value1, String value2) {
            addCriterion("WORKFLOW_KEY not between", value1, value2, "workflowKey");
            return (Criteria) this;
        }

        public Criteria andAccessControlIsNull() {
            addCriterion("ACCESS_CONTROL is null");
            return (Criteria) this;
        }

        public Criteria andAccessControlIsNotNull() {
            addCriterion("ACCESS_CONTROL is not null");
            return (Criteria) this;
        }

        public Criteria andAccessControlEqualTo(Long value) {
            addCriterion("ACCESS_CONTROL =", value, "accessControl");
            return (Criteria) this;
        }

        public Criteria andAccessControlNotEqualTo(Long value) {
            addCriterion("ACCESS_CONTROL <>", value, "accessControl");
            return (Criteria) this;
        }

        public Criteria andAccessControlGreaterThan(Long value) {
            addCriterion("ACCESS_CONTROL >", value, "accessControl");
            return (Criteria) this;
        }

        public Criteria andAccessControlGreaterThanOrEqualTo(Long value) {
            addCriterion("ACCESS_CONTROL >=", value, "accessControl");
            return (Criteria) this;
        }

        public Criteria andAccessControlLessThan(Long value) {
            addCriterion("ACCESS_CONTROL <", value, "accessControl");
            return (Criteria) this;
        }

        public Criteria andAccessControlLessThanOrEqualTo(Long value) {
            addCriterion("ACCESS_CONTROL <=", value, "accessControl");
            return (Criteria) this;
        }

        public Criteria andAccessControlIn(List<Long> values) {
            addCriterion("ACCESS_CONTROL in", values, "accessControl");
            return (Criteria) this;
        }

        public Criteria andAccessControlNotIn(List<Long> values) {
            addCriterion("ACCESS_CONTROL not in", values, "accessControl");
            return (Criteria) this;
        }

        public Criteria andAccessControlBetween(Long value1, Long value2) {
            addCriterion("ACCESS_CONTROL between", value1, value2, "accessControl");
            return (Criteria) this;
        }

        public Criteria andAccessControlNotBetween(Long value1, Long value2) {
            addCriterion("ACCESS_CONTROL not between", value1, value2, "accessControl");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table OBERACIIS_FUEL.T_MODULE
     *
     * @mbggenerated do_not_delete_during_merge Thu May 14 11:49:07 CST 2015
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table OBERACIIS_FUEL.T_MODULE
     *
     * @mbggenerated Thu May 14 11:49:07 CST 2015
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}