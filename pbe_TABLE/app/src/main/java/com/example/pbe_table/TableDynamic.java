package com.example.pbe_table;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class TableDynamic {
        private TableLayout tableLayout;
        private Context context;
        private String[] header;
        private ArrayList<String[]> data;
        private TableRow tableRow;
        private TextView txtCell;
        private int indexC;
        private int indexR;
        private boolean multiColor=false;
        int firstColor;
        int secondColor;
        public TableDynamic(TableLayout tableLayout, Context context) {
            this.tableLayout=tableLayout;
            this.context=context;
        }
        public void  addHeader(String[]header){
            this.header=header;
            createHeader();
        }
        public void  addData(ArrayList<String[]>data){
            this.data=data;
            createDataTable();
        }
        private void newRow(){
            tableRow=new TableRow(context);
        }

        private void newCell(){
            txtCell=new TextView(context);
            txtCell.setGravity(Gravity.CENTER);
            txtCell.setTextSize(25);
        }

        private void createHeader(){
            indexC=0;
            newRow();
            while (indexC<header.length){
                newCell();
                txtCell.setText(header[indexC++]);
                tableRow.addView(txtCell,newTableRowParams());
            }
            tableLayout.addView(tableRow);
        }
        private void createDataTable(){
            String info;

            for (indexR=1; indexR<=this.data.size(); indexR++){
                newRow();

                for (indexC=0; indexC<header.length; indexC++){
                    newCell();

                    String[] row=this.data.get(indexR-1);
                    info=(indexC<row.length)?row[indexC]:"";
                    txtCell.setText(info);
                    tableRow.addView(txtCell,newTableRowParams());
                }
                tableLayout.addView(tableRow);
            }
        }

        public void deleteAll(TableLayout table){
            if(!data.isEmpty()) {
                this.data.clear();
            }
            table.removeAllViews();
            tableLayout.removeAllViews();

        }
        public void backgroundHeader(int color){
            indexC=0;
            newRow();
            while (indexC<header.length){
                txtCell=getCell(0,indexC++);
                txtCell.setBackgroundColor(color);
            }
        }
        public void backgroundData(int firstColor, int secondColor){
            multiColor=true;
            for (indexR=1; indexR<=this.data.size(); indexR++){

                multiColor=!multiColor;
                for (indexC=0; indexC<header.length; indexC++){
                    txtCell=getCell(indexR,indexC);
                    txtCell.setBackgroundColor((multiColor)?firstColor:secondColor);
                }
            }
            this.firstColor =firstColor;
            this.secondColor=secondColor;
        }

        private TableRow getRow(int index){
            return (TableRow)tableLayout.getChildAt(index);
        }

        private TextView getCell(int rowIndex,int columIndex){
            tableRow=getRow(rowIndex);
            return (TextView) tableRow.getChildAt(columIndex);
        }
        private TableRow.LayoutParams newTableRowParams(){
            TableRow.LayoutParams params=new TableRow.LayoutParams();
            params.setMargins(1,1,1,1);
            params.weight=1;
            return params;
        }
    }

