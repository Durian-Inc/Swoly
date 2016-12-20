package com.tripidevs.swoly;

import java.util.ArrayList;

public class weights
{
  //used for converting from imperial to metric
  //divide 1 by it to get metric to imperial
  private static double metricConversion=0.4536;
  //constructor
  public weights(double nValue, boolean nMetric)
  {
    if(nMetric)
    {
      nValue*=1/metricConversion;
    }
    value=nValue;
    metric = nMetric;
  }
  public void setValue(double nValue){value=nValue;}
  public double getValue()
  {
    double nValue=value;
    if(metric)
      nValue*=metricConversion;
    return nValue;
  }
  public void setMetric(boolean nMetric){metric=nMetric;}
  public boolean getMetric(){return metric;}
  private double value;
  private boolean metric;
}