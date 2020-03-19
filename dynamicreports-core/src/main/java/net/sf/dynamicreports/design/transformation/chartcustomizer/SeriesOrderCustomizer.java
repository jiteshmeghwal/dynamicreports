/*
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 - 2018 Ricardo Mariaca and the Dynamic Reports Contributors
 * http://www.dynamicreports.org
 *
 * This file is part of DynamicReports.
 *
 * DynamicReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * DynamicReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with DynamicReports. If not, see <http://www.gnu.org/licenses/>.
 */
package net.sf.dynamicreports.design.transformation.chartcustomizer;

import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.constant.OrderType;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.report.definition.chart.DRIChartCustomizer;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.CategoryDataset;

import java.io.Serializable;
import java.util.Comparator;

/**
 * <p>SeriesOrderCustomizer class.</p>
 *
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 * 
 */
public class SeriesOrderCustomizer implements DRIChartCustomizer, Serializable {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private Comparator<String> seriesOrderBy;
    private OrderType seriesOrderType;

    /**
     * <p>Constructor for SeriesOrderCustomizer.</p>
     *
     * @param seriesOrderBy   a {@link java.util.Comparator} object.
     * @param seriesOrderType a {@link net.sf.dynamicreports.report.constant.OrderType} object.
     */
    public SeriesOrderCustomizer(Comparator<String> seriesOrderBy, OrderType seriesOrderType) {
        this.seriesOrderBy = seriesOrderBy;
        this.seriesOrderType = seriesOrderType;
    }

    /** {@inheritDoc} */
    @Override
    public void customize(JFreeChart chart, ReportParameters reportParameters) {
        if (chart.getPlot() instanceof CategoryPlot) {
            CategoryDataset dataset = new SeriesOrderCategoryDataset(chart.getCategoryPlot().getDataset(), seriesOrderBy, seriesOrderType);
            chart.getCategoryPlot().setDataset(dataset);
        }
    }
}
