import { Department } from "@/api/types";

type DepartmentsContainerProps = {
  departments: Department[];
};

export default function DepartmentsContainer({
  departments,
}: DepartmentsContainerProps) {
  return (
    <>
      <div className="w-full h-14 border-b border-gray-300 flex justify-between items-center">
        <h1 className="mx-10 text-lg leading-14 text-gray-700 font-normal">
          部署一覧
        </h1>
      </div>
      <table className="w-full border-collapse">
        <thead>
          <tr className="bg-gray-100 font-bold text-xs border-b border-gray-300">
            <td className="p-4">部署コード</td>
            <td className="p-4">部署名</td>
            <td className="p-4">親部署コード</td>
          </tr>
        </thead>
        <tbody>
          {departments.map((department: Department) => (
            <tr
              key={department.code.value}
              className="border-b border-gray-300"
            >
              <td className="p-4">{department.code.value}</td>
              <td className="p-4">{department.name.value}</td>
              <td className="p-4">{department.parentCode.value || ""}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </>
  );
}
