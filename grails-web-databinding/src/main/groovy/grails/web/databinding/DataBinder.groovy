/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package grails.web.databinding

import grails.databinding.CollectionDataBindingSource
import groovy.transform.CompileStatic
import org.springframework.validation.BindingResult

/**
 *
 * Methods added to enable binding data (typically incoming request parameters) to objects and collections
 *
 * @author Jeff Brown
 * @author Graeme Rocher
 *
 * @since 3.0
 *
 */
@CompileStatic
trait DataBinder {

    BindingResult bindData(target, bindingSource, Map includeExclude) {
        bindData target, bindingSource, includeExclude, null
    }

    BindingResult bindData(target, bindingSource) {
        bindData target, bindingSource, Collections.EMPTY_MAP, null
    }

    BindingResult bindData(target, bindingSource, String filter) {
        bindData target, bindingSource, Collections.EMPTY_MAP, filter
    }

    BindingResult bindData(target, bindingSource, List excludes) {
        bindData target, bindingSource, [exclude: excludes], null
    }

    BindingResult bindData(target, bindingSource, List excludes, String filter) {
        bindData target, bindingSource, [exclude: excludes], filter
    }

    BindingResult bindData(target, bindingSource, Map includeExclude, String filter) {
        def include = includeExclude?.include
        def exclude = includeExclude?.exclude
        List includeList = include instanceof String ? [include]: (List)include
        List excludeList = exclude instanceof String ? [exclude]: (List)exclude
        DataBindingUtils.bindObjectToInstance target, bindingSource, includeList, excludeList, filter
    }

    void bindData(Class targetType, Collection collectionToPopulate, CollectionDataBindingSource collectionBindingSource) {
        DataBindingUtils.bindToCollection targetType, collectionToPopulate, collectionBindingSource
    }

}
