"use client";
import MyDropzone from "../../components/dragndrop";
import { useEffect, useState } from "react";
import { Input, Link, Button, Tooltip } from "@nextui-org/react";
import React from "react";
import {
  Table,
  TableHeader,
  TableColumn,
  TableBody,
  TableRow,
  TableCell,
} from "@nextui-org/react";
import { DeleteIcon } from "../../components/DeleteIcon";
import { Progress } from "@nextui-org/react";
import { IoMdDownload } from "react-icons/io";
import { ip } from "../auth/AuthenticationProvider";
import IntervalSelector from "../intervalSelector";
import { INTERVALS } from "../intervals";
import { SelectMenuOption } from "../intervalSelectorTypes";

const AssetsComponent = () => {
  const [rows, setRows] = useState([]);
  const [tableData, setTableData] = useState([]);
  const [selectedFile, setSelectedFile] = useState(null);
  const [maxFiles, setMaxFiles] = useState(0);
  const [label, setLabel] = useState("");
  const [fileLabel, setFileLabel] = useState("");
  const [uploadedFileAmount, setUploadedFileAmount] = useState();
  const [maxFileSize, setMaxFileSize] = useState(0);
  const [email, setEmail] = useState("");
  const [assetName, setAssetName] = useState("");
  const [value, setValue] = React.useState("");
  const [selectedKeys, setSelectedKeys] = useState(new Set(["month/s"]));
  const [intervalData, setIntervalData] = useState("");
  const [interval, setInterval] = useState("days");
  const [isOpen, setIsOpen] = useState(false);
  const [fileLimit, setFileLimit] = useState(0);

  useEffect(() => {
    listAssets().then((data) => {
      setRows(data);
    });

    getMaxSize().then((data) => {
      setLabel("Max file storage: " + (data.maxSize / Math.pow(1024, 2)).toFixed(2) + " MB");
      setMaxFileSize(data.maxSize);
    });

    getMaxFiles().then((data) => {
      setMaxFiles(data.maxFiles);
    });

    fetchUserData().then((data) => {
      setIntervalData(data.interval);
	  setFileLimit(data.fileLimit);
	  setFileLabel("Max amount of files: " + data.fileLimit);
    });
  }, []);

  async function sendChangeIntervalRequest() {
    const url = "http://" + ip + "/auth/changeinterval";

    const headers = {
      Authorization: "Bearer " + localStorage.getItem("token"),
      "Content-Type": "application/json",
    };

    let intervalDays = 0;
    if (interval == "") return;

    if (interval == "days") {
      intervalDays = parseInt(intervalData);
    } else if (interval == "weeks") {
      intervalDays = parseInt(intervalData) * 7;
    } else {
      intervalDays = parseInt(intervalData) * 31;
    }

    const params = {
      interval: intervalDays,
    };

    const options = {
      method: "POST",
      headers: headers,
      body: JSON.stringify(params),
    };

    fetch(url, options).then((response: Response) => {
		return response.json();
	}).catch((error) => {
		console.error(error);
	});
  }

  async function fetchUserData() {
    const url = "http://" + ip + "/auth/getuserdata";
    const headers = {
      Authorization: "Bearer " + localStorage.getItem("token"),
    };
    const options = {
      method: "GET",
      headers: headers,
    };

    return fetch(url, options)
      .then((response: Response) => {
        return response.json();
      })
      .catch((error) => {
        console.error(error);
      });
  }

  async function handleDownload(fileName, contact) {
    const headers = {
      Authorization: "Bearer " + localStorage.getItem("token"),
    };

    const formData = new FormData();
    formData.append("filename", fileName);
    formData.append("contact", contact);

    try {
      const response = await fetch("http://" + ip + "/assets/download", {
        method: "POST",
        body: formData,
        headers: headers,
      });

      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }

      if (response.body) {
        const blob = await response.blob();

        const url = window.URL.createObjectURL(blob);

        const link = document.createElement("a");
        link.href = url;
        link.setAttribute("download", fileName);
        document.body.appendChild(link);
        link.click();
        setTimeout(() => {
          window.URL.revokeObjectURL(url);
          link.remove();
        }, 100);
      }
    } catch (error) {
      console.error("Error while downloading Assets", error);
    }
  }

  async function handleDelete(fileName: any, contact: any) {
    const formData = new FormData();
    formData.append("filename", fileName);
    formData.append("contact", contact);

    const headers = {
      Authorization: "Bearer " + localStorage.getItem("token"),
    };

    try {
      const response = await fetch("http://" + ip + "/assets/deletefile", {
        method: "POST",
        body: formData,
        headers: headers,
      });

      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }

      window.location.href = "/assets";
    } catch (error) {
      console.error("Error deleting file:", error);
    }
  }

  async function listAssets() {
    const headers = {
      Authorization: "Bearer " + localStorage.getItem("token"),
    };

    try {
      const response = await fetch("http://" + ip + "/assets/list", {
        method: "POST",
        headers: headers,
      });

      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }

      const data = await response.json();
      setUploadedFileAmount(data.length);
	  return data;
    } catch (error) {
      console.error("Error when fetching Assets", error);
    }
  }

  async function getMaxFiles() {
    const headers = {
      Authorization: "Bearer " + localStorage.getItem("token"),
    };

    try {
      const response = await fetch("http://" + ip + "/assets/getMaxFiles", {
        method: "POST",
        headers: headers,
      });

      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }

      const data = await response.json();
      return data;
    } catch (error) {
      console.error("Error when fetching max Files", error);
    }

  }

  async function getMaxSize() {
    const headers = {
      Authorization: "Bearer " + localStorage.getItem("token"),
    };

    try {
      const response = await fetch("http://" + ip + "/assets/getMaxSize", {
        method: "POST",
        headers: headers,
      });

      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }

      const data = await response.json();
      return data;
    } catch (error) {
      console.error("Error when fetching Assets", error);
    }
  }

  async function handleButtonClick() {
    if (!selectedFile) {
      alert("Please select a file");
      return;
    }

    if (!email) {
      alert("Please type in a contact");
    }

    const formData = new FormData();
    formData.append("file", selectedFile);
    formData.append("filename", assetName);
    formData.append("contact", email);

    const headers = {
      Authorization: "Bearer " + localStorage.getItem("token"),
    };

    try {
      const response = await fetch("http://" + ip + "/assets/upload", {
        method: "POST",
        body: formData,
        headers: headers,
      });

      if (!response.ok) {
        if (response.status == 400) {
          alert("You cannot upload more than " + maxFiles + " files!");
          return;
        }

        throw new Error(`HTTP error! status: ${response.status}`);
      }

      window.location.href = "/assets";
    } catch (error) {
      console.error("Error uploading file:", error);
    }
  }

  const columns = [
    {
      key: "name",
      label: "NAME",
    },
    {
      key: "contact",
      label: "CONTACT",
    },
    {
      key: "size",
      label: "SIZE",
    },
    {
      key: "actions",
      label: "ACTIONS",
    },
  ];

  const handleFileDrop = (acceptedFiles) => {
    setSelectedFile(acceptedFiles[0]);
    setAssetName(acceptedFiles[0].name);
  };

  const renderCell = React.useCallback((rowData, columnKey) => {
    switch (columnKey) {
      case "name":
        return rowData.name;

      case "contact":
        return rowData.contact;

      case "size":
        return (rowData.size / Math.pow(1024, 2)).toFixed(2) + " MB";

      case "actions":
        return (
          <div className="flex justify-center">
            <div className="mr-4">
              <Tooltip color="primary" content="Download Asset">
                <span className="text-lg text-primary cursor-pointer active:opacity-50">
                  <IoMdDownload
                    onClick={() =>
                      handleDownload(rowData.name, rowData.contact)
                    }
                  />
                </span>
              </Tooltip>
            </div>

            <div className="ml-4 mr-7">
              <Tooltip color="danger" content="Delete Asset">
                <span className="text-lg text-danger cursor-pointer active:opacity-50">
                  <DeleteIcon
                    onClick={() => handleDelete(rowData.name, rowData.contact)}
                  />
                </span>
              </Tooltip>
            </div>
          </div>
        );
    }
  }, []);

  const handleEmailChange = (event: {
    target: { value: React.SetStateAction<string> };
  }) => {
    setEmail(event.target.value);
  };

  const handleAssetNameChange = (event: {
    target: { value: React.SetStateAction<string> };
  }) => {
    setAssetName(event.target.value);
  };

  const validateEmail = (value: string) => {
    const emailRegex = /^[^\W_]+(\.[^\W_]+)*@[^\W_]+(\.[^\W_]+)*\.[^\W_]{2,}$/;
    return emailRegex.test(value);
  };

  const isInvalid = React.useMemo(() => {
    if (value === "") return false;
    return !validateEmail(value);
  }, [value]);

  const handleIntervalDataChange = (event: any) => {
    setIntervalData(event.target.value);
  };

  const getTotalSize = (rows) => {
    return rows.reduce((total, row) => total + Number(row.size), 0);
  };

  const totalSize = getTotalSize(rows);

  return (
    <div className="mt-[5rem] text-center justify-center">
      <h1 className="text-5xl">Add new Assets</h1>
      <p className="mt-[1rem] text-xl">Lorem Ipsum Lorem Ipsum Lorem Ipsum</p>

      <div className="mt-[5rem] flex justify-center">
        <div className="bg-gray-100 dark:bg-[#222] w-full sm:w-2/3 py-10">
          <MyDropzone onFileDrop={handleFileDrop} />

          <div className="mt-[5rem] flex flex-col sm:flex-row mb-12 space-y-4 sm:space-y-0 mx-10">
            <Input
              autoFocus
              label="Email"
              placeholder="Enter your email"
              variant="bordered"
              isInvalid={isInvalid}
              color={isInvalid ? "danger" : "primary"}
              errorMessage={isInvalid && "Please enter a valid email"}
              onValueChange={setValue}
              value={email}
              onChange={handleEmailChange}
              type="email"
              id="email"
              className="w-full sm:w-2/3 mx-0 sm:mx-10"
            />
            <Input
              isDisabled
              label="Name"
              placeholder={assetName}
              variant="bordered"
              type="text"
              color="primary"
              id="text"
              className="w-full sm:w-2/3 mx-0 sm:mx-10"
              value={assetName}
              onChange={handleAssetNameChange}
            />
          </div>

          <Link
            onClick={handleButtonClick}
            className="px-6 py-2 text-white bg-gradient-to-r from-indigo-500 to-blue-500 rounded-md md:ml-5 w-2/6 sm:w-3/12 justify-center"
          >
            Add Asset
          </Link>
        </div>
      </div>

      <div className="flex justify-center">
        <div className="bg-gray-100 dark:bg-[#222] w-full sm:w-2/3 py-10 my-20">
          <h1 className="text-5xl">Your Activity</h1>

          <form className="mt-[3rem] flex flex-col">
            <h2 className="text-2xl ">Interval Settings</h2>

            <div className="mt-[3rem] justify-center">
              <div className="flex flex-row justify-center">
                <p className="mt-[1rem] mr-[2rem]"> Set your time interval </p>
                <Input
                  label="Interval"
                  type="number"
                  placeholder="3"
                  className="w-1/4 mr-[2rem]"
                  value={intervalData || ""}
                  onChange={handleIntervalDataChange}
                />

                <div className="w-[10rem]">
                  <IntervalSelector
                    id={"intervals"}
                    open={isOpen}
                    onToggle={() => setIsOpen(!isOpen)}
                    onChange={(val) => setInterval(val)}
                    selectedValue={
                      INTERVALS.find(
                        (option) => option.value === interval
                      ) as SelectMenuOption
                    }
                  />
                </div>
              </div>

              <Button
                className="mt-[1rem] bg-indigo-600 text-white"
                type="submit"
                onClick={sendChangeIntervalRequest}
              >
                Update Interval
              </Button>
            </div>
          </form>
        </div>
      </div>

      <div className="flex justify-center">
        <div className="bg-gray-100 dark:bg-[#222] py-10 w-full sm:w-2/3">
          <div className="flex justify-center ">
        	<div className="flex justify-center mb-5 ml-14 sm:ml-0 w-8/12 sm:w-7/12" >
				<Progress
              	label={label}
              	size="sm"
              	value={totalSize}
              	maxValue={maxFileSize}
              	color="primary"
              	showValueLabel={true}
              	className="max-w-md"
            	/>
			</div>
			<div className="flex justify-center mb-5 ml-14 sm:ml-0 w-8/12 sm:w-7/12">
				<Progress
              	label={fileLabel}
              	size="sm"
              	value={uploadedFileAmount}
              	maxValue={fileLimit}
              	color="primary"
              	showValueLabel={true}
              	className="max-w-md"
            	/>
			</div>
          </div>
          
		  <div className="flex justify-center">
            <div className="bg-gray-100 dark:bg-[#222] py-10 w-full sm:w-2/3">
              <div className="flex justify-center">
                <Table className="w-4/5">
                  <TableHeader columns={columns}>
                    {(column) => (
                      <TableColumn key={column.key}>{column.label}</TableColumn>
                    )}
                  </TableHeader>

                  <TableBody items={rows}>
                    {(item) => (
                      <TableRow key={rows.indexOf(item)}>
                        {(columnKey) => (
                          <TableCell>{renderCell(item, columnKey)}</TableCell>
                        )}
                      </TableRow>
                    )}
                  </TableBody>
                </Table>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};
export default AssetsComponent;
