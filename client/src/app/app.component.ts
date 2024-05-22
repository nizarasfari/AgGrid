import { Component,OnInit } from '@angular/core';
import { AgGridAngular } from 'ag-grid-angular'; // Angular Data Grid Component
import { ColDef } from 'ag-grid-community'; // Column Definition Type Interface
import 'ag-grid-community/styles/ag-grid.css';
/* Quartz Theme Specific CSS */
import 'ag-grid-community/styles/ag-theme-quartz.css';

import { FileService } from './file.service';



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
 
 export class AppComponent implements OnInit{
  rowData: any[] = [];
  colDefs: ColDef[] = [
    { field: "fileName", headerName: "File Name" }
  ];

  constructor(private fileService: FileService) { }

  ngOnInit(): void {
    this.loadFiles();
  }

  loadFiles(): void {
    this.fileService.getFiles().subscribe(
      data => {
        this.rowData = data.map(fileName => ({fileName}))
        console.log('Files fetched', this.rowData);
      },
      error => {
        console.error('Error fetching files', error);
      }
    );
  }
 }
 