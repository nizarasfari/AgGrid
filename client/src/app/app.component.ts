import { Component } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { AgGridModule } from 'ag-grid-angular';
import { FileService } from './file.service';
import 'ag-grid-community/styles/ag-grid.css';
/* Quartz Theme Specific CSS */
import 'ag-grid-community/styles/ag-theme-quartz.css';
@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, HttpClientModule, AgGridModule],
  providers: [FileService],
  template: `<ag-grid-angular
  style="width: 100%; height: 600px;"
  class="ag-theme-quartz"
  [rowData]="rowData"
  [columnDefs]="columnDefs"
  [pagination]="true"
  [paginationPageSize]="paginationPageSize"
  [rowModelType]="'infinite'"
  [cacheBlockSize]="paginationPageSize"
  [maxBlocksInCache]="maxBlocksInCache"
  (gridReady)="onGridReady($event)">
</ag-grid-angular>`,
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  rowData: any[] = [];
  columnDefs = [
    { field: 'id', headerName: 'ID' },
    { field: 'email', headerName: 'Email' },
    { field: 'gender', headerName: 'Gender' },
    { field: 'company', headerName: 'Company' },
    { field: 'first_name', headerName: 'First Name' },
    { field: 'last_name', headerName: 'Last Name' }
  ];
  paginationPageSize = 5;
  maxBlocksInCache = 5;

  constructor(private fileService: FileService) {}

  onGridReady(params: any) {
    params.api.setDatasource(this.createDataSource());
  }

  createDataSource() {
    return {
      getRows: (params: any) => {
        const page = params.startRow / this.paginationPageSize;
        this.fileService.getFiles(page, this.paginationPageSize).subscribe(data => {
          params.successCallback(data.person, data.totalPersons);
        }, error => {
          params.failCallback();
        });
      }
    };
  }
}
