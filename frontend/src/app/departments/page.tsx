import { getDepartments } from "@/api/departments";
import DepartmentsContainer from "./_departmentContainer.tsx/DepartmentsContainer";

export default async function Departments() {
  const departments = await getDepartments();
  return <DepartmentsContainer departments={departments} />;
}
