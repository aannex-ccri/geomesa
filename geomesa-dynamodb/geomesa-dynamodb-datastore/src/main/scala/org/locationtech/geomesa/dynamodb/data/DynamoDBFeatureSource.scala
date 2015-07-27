/***********************************************************************
* Copyright (c) 2013-2016 Commonwealth Computer Research, Inc.
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Apache License, Version 2.0
* which accompanies this distribution and is available at
* http://www.opensource.org/licenses/apache2.0.php.
*************************************************************************/

package org.locationtech.geomesa.dynamodb.data

import com.amazonaws.services.dynamodbv2.document.Table
import org.geotools.data.{FeatureWriter, FeatureReader, Query}
import org.geotools.data.store.{ContentFeatureStore, ContentEntry}
import org.geotools.geometry.jts.ReferencedEnvelope
import org.opengis.feature.simple.{SimpleFeature, SimpleFeatureType}

class DynamoDBFeatureSource(entry: ContentEntry,
                            sft: SimpleFeatureType,
                            table: Table)
  extends ContentFeatureStore(entry, Query.ALL) {

  override def buildFeatureType(): SimpleFeatureType = sft

  override def getBoundsInternal(query: Query): ReferencedEnvelope = ???

  override def getCountInternal(query: Query): Int = ???

  override def getReaderInternal(query: Query): FeatureReader[SimpleFeatureType, SimpleFeature] = ???

  override def getWriterInternal(query: Query, flags: Int): FeatureWriter[SimpleFeatureType, SimpleFeature] =
    new DynamoDBFeatureWriter(sft, table)
}