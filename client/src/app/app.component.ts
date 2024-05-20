import { Component } from '@angular/core';
import { AgGridAngular } from 'ag-grid-angular'; // Angular Data Grid Component
import { ColDef } from 'ag-grid-community'; // Column Definition Type Interface
import 'ag-grid-community/styles/ag-grid.css';
/* Quartz Theme Specific CSS */
import 'ag-grid-community/styles/ag-theme-quartz.css';



@Component({
  selector: 'app-root',
  standalone: true,
  imports: [AgGridAngular], // Add Angular Data Grid Component
  styleUrls: ['./app.component.css'],
  template: `
  <ag-grid-angular
  class="ag-theme-quartz"
 style="height: 500px;"
  [rowData]="rowData"
  [columnDefs]="colDefs">
</ag-grid-angular>`
 })
 
 export class AppComponent {
  // Row Data: The data to be displayed.
  rowData = [
    { make: "Tesla", model: "Model Y", price: 64950, electric: true },
    { make: "Ford", model: "F-Series", price: 33850, electric: false },
    { make: "Toyota", model: "Corolla", price: 29600, electric: false },
  ];
 
  // Column Definitions: Defines the columns to be displayed.
  colDefs: ColDef[] = [
    { field: "make" },
    { field: "model" },
    { field: "price" },
    { field: "electric" }
  ];
 }
 