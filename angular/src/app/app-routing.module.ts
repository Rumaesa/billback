import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DistributorMappingComponent } from './components/distributor-mapping/distributor-mapping.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';

const routes: Routes = [
  {path: '', component: DashboardComponent},
  { path: 'distributor-mapping', component: DistributorMappingComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
